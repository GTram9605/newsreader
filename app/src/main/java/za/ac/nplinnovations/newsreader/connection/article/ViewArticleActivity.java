package za.ac.nplinnovations.newsreader.connection.article;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import za.ac.nplinnovations.newsreader.R;
import za.ac.nplinnovations.newsreader.SplashActivity;
import za.ac.nplinnovations.newsreader.connection.article.adapter.AdapterArticle;
import za.ac.nplinnovations.newsreader.connection.pojos.Article;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public class ViewArticleActivity extends AppCompatActivity {
    private Article mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.getSerializableExtra(AdapterArticle.SELECTED_ARTICLE) != null){
            mData = (Article) intent.getSerializableExtra(AdapterArticle.SELECTED_ARTICLE);
            toolBarLayout.setTitle(mData.getTitle());

            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            TextView tvAuthors = (TextView) findViewById(R.id.tvAuthors);
            TextView tvPublishdate = (TextView) findViewById(R.id.tvPublishdate);
            TextView tvAbstract = (TextView) findViewById(R.id.tvAbstract);

            tvTitle.setText(mData.getTitle());
            tvAuthors.setText(mData.getByline());
            tvPublishdate.setText(mData.getPublished_date());
            tvAbstract.setText(mData.getAbstract());

            ImageView ivOne = (ImageView) findViewById(R.id.ivOne);

            Picasso.get()
                    .load(mData.getMedia().get(0).getMedia_metadata().get(0).getUrl())
                    .placeholder(R.drawable.no_image_icon)
                    .error(R.drawable.no_image_found)
                    .into(ivOne);

            TextView tvAdditionalData = (TextView) findViewById(R.id.tvAdditionalData);
            String data = "Caption: " + mData.getMedia().get(0).getCaption() 
                    + "\nCopyright: " + mData.getMedia().get(0).getCopyright()+ "\n\n" ;

            for (String facet:
                 mData.getDes_facet()) {
                data+= facet + ", ";
            }
            
            tvAdditionalData.setText(data);
        }else
            toolBarLayout.setTitle(getTitle());

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

