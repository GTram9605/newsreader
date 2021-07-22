package za.ac.nplinnovations.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.Serializable;

import za.ac.nplinnovations.newsreader.connection.article.ListArticlesActivity;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private final Gson gson = new Gson();
    public static final String ARTICLES = "articles";
    private TextView tvErrorMessage;
    private ProgressBar pgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);


    }

    public void onClickStart(View view) {
        pgLoading.setVisibility(View.VISIBLE);
        final Intent intent = new Intent(view.getContext(), ListArticlesActivity.class);

        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = (Connection.getUrl("viewed", 7));
        String url = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=JGGKCKZJTTwd6NUWsQe3GhrQL7AjbIMh";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "JSON response: " + response.toString());
                MainResponse mainResponse = gson.fromJson(response.toString(), MainResponse.class);

                Log.d(TAG, mainResponse.getCopyright() + "\n"
                        + mainResponse.getResults().size() + "\n"
                        + mainResponse.getNum_results());

                intent.putExtra(ARTICLES, (Serializable) mainResponse);
                pgLoading.setVisibility(View.GONE);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "=================================================================================");
                Log.d(TAG, "Failure: " + error.getLocalizedMessage());
                Log.d(TAG, "Failure: " + error.getMessage());
                Log.d(TAG, "Failure: " + error.getCause());
                tvErrorMessage.setText(error.getLocalizedMessage());

                pgLoading.setVisibility(View.GONE);
                tvErrorMessage.setVisibility(View.VISIBLE);
            }
        });

        queue.add(jsonObjectRequest);
        
    }
}