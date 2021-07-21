package za.ac.nplinnovations.newsreader.connection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.ac.nplinnovations.newsreader.connection.pojos.MainResponse;

public interface QueryService {
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    @GET( "7/")
    Call<MainResponse> loadData(@Query("api-key") String apikey);
}
