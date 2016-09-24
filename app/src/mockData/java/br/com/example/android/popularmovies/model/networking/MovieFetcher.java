package br.com.example.android.popularmovies.model.networking;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.example.android.popularmovies.BuildConfig;
import br.com.example.android.popularmovies.model.data.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieFetcher {
    private static final String TAG = MovieFetcher.class.getName();

    private static MovieFetcher instance;

    private MovieFetcher() {
    }

    public static MovieFetcher getInstance() {
        if (instance == null) {
            instance = new MovieFetcher();
        }

        return instance;
    }

    public void fetchMovies(final OnMoviesFetchedListener moviesFetchedListener) {
        new AsyncTask<Void, Void, List<Movie>>() {

            @Override
            protected List<Movie> doInBackground(Void... voids) {
                List<Movie> movies = new ArrayList<Movie>();

                //TODO add mock movies

                return movies;
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                if (movies != null) {
                    moviesFetchedListener.onMoviesFetched(movies);
                } else {
                    moviesFetchedListener.onError();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //TODO move this outside here
    public interface OnMoviesFetchedListener {
        void onMoviesFetched(List<Movie> movies);

        void onError();
    }

}
