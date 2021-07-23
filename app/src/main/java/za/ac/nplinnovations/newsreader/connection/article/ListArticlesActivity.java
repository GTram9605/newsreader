package za.ac.nplinnovations.newsreader.connection.article;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import za.ac.nplinnovations.newsreader.R;
import za.ac.nplinnovations.newsreader.SplashActivity;
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

        tvCopyright.setText(mData.getCopyright());

        adapter = new AdapterArticle(mData, this);
        if (mData.getNum_results() > 0){
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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