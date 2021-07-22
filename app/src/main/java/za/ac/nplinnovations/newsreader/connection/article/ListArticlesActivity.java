package za.ac.nplinnovations.newsreader.connection.article;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    }
}