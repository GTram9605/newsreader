package za.ac.nplinnovations.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import za.ac.nplinnovations.newsreader.connection.Connection;
import za.ac.nplinnovations.newsreader.connection.QueryService;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    private final Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    public void onClickStart(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = (Connection.getUrl("viewed", 7));
        String url = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=JGGKCKZJTTwd6NUWsQe3GhrQL7AjbIMh";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "=================================================================================" +
                        "\n=================================================================================");
                Log.d(TAG, "JSON response: " + response.toString());
                MainResponse mainResponse = gson.fromJson(response.toString(), MainResponse.class);

                Log.d(TAG, mainResponse.getCopyright() + "\n"
                        + mainResponse.getResults().size() + "\n"
                        + mainResponse.getNum_results());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "=================================================================================");
                Log.d(TAG, "Failure: " + error.getLocalizedMessage());
                Log.d(TAG, "Failure: " + error.getMessage());
                Log.d(TAG, "Failure: " + error.getCause());
            }
        });

        queue.add(jsonObjectRequest);

        /*Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Connection.getUrl("viewed", 7))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        QueryService service = retrofit.create(QueryService.class);

        Call<MainResponse> response = service.loadData(Connection.getApiKey());
        response.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.d(TAG, "=================================================================================" +
                        "\n=================================================================================");
                Log.d(TAG, "JSON response: " + response.body().toString());
                Log.d(TAG, "Response message: " + response.message());
                Log.d(TAG, "JSON response: isSuccessful?" + response.isSuccessful());
                Log.d(TAG, "Error body: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.d(TAG, "=================================================================================");
                Log.d(TAG, "Failure: " + t.getLocalizedMessage());
                Log.d(TAG, "Failure: " + t.getMessage());
            }
        });*/
    }
}