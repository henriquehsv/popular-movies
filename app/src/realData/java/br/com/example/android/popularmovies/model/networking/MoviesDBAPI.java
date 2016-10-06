package br.com.example.android.popularmovies.model.networking;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MoviesDBAPI {

    @GET("/3/movie/popular")
    Observable<JsonObject> popularMovies(@Query("api_key") String apiKey);
}
