package za.ac.nplinnovations.newsreader.connection.article;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.Serializable;

import za.ac.nplinnovations.newsreader.R;
import za.ac.nplinnovations.newsreader.SplashActivity;
import za.ac.nplinnovations.newsreader.connection.Connection;
import za.ac.nplinnovations.newsreader.connection.article.adapter.AdapterArticle;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public class ListArticlesActivity extends AppCompatActivity {
    private MainResponse mData;
    private RecyclerView rvMain;
    private TextView tvNoData;
    private TextView tvCopyright;
    private Spinner spinnerCategory, spinnerDays;
    private AdapterArticle adapter;

    private String category;
    private int period;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        category = "viewed";
        period = 7;


        Intent intent = getIntent();
        if (intent.getSerializableExtra(SplashActivity.ARTICLES) != null){
            mData = (MainResponse)intent.getSerializableExtra(SplashActivity.ARTICLES);
        }

        rvMain = (RecyclerView) findViewById(R.id.rvMain);
        tvNoData = (TextView) findViewById(R.id.tvNoData);
        tvCopyright = (TextView) findViewById(R.id.tvCopyright);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerDays = (Spinner) findViewById(R.id.spinnerDays);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
       // reloadArticles();
        if (mData != null) tvCopyright.setText(mData.getCopyright());

        adapter = new AdapterArticle(mData, this);
        if (mData != null && mData.getNum_results() > 0){
            rvMain.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            rvMain.setAdapter(adapter);
        }else {
            rvMain.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        category = "emailed";
                        break;

                    case 1:
                        category = "facebook";
                        break;

                    case 2:
                        category = "viewed";
                        break;
                }

                reloadArticles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        period = 1;
                        break;

                    case 1:
                        period = 7;
                        break;

                    case 2:
                        period = 30;
                        break;
                }
                reloadArticles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void reloadArticles() {
        pbLoading.setVisibility(View.VISIBLE);
        rvMain.setVisibility(View.GONE);
        tvNoData.setVisibility(View.GONE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = (Connection.getUrl(category, period));
        final Gson gson = new Gson();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mData = gson.fromJson(response.toString(), MainResponse.class);
                adapter = new AdapterArticle(mData, getBaseContext());
                if (mData.getNum_results() > 0){
                    rvMain.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.GONE);
                    rvMain.setAdapter(adapter);
                    pbLoading.setVisibility(View.GONE);
                }else {
                    rvMain.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                    pbLoading.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rvMain.setVisibility(View.GONE);
                tvNoData.setVisibility(View.VISIBLE);
                Log.d("Exception", error.getMessage());
                Log.d("Exception", error.getLocalizedMessage());
                Log.d("Exception", error.getCause().toString());
                pbLoading.setVisibility(View.GONE);
            }
        });

        queue.add(jsonObjectRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}