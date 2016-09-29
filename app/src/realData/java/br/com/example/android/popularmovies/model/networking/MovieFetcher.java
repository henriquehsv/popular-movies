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
import java.util.Arrays;
import java.util.List;

import br.com.example.android.popularmovies.BuildConfig;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.data.OnMoviesFetchedListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieFetcher {
    private static final String BASE_URL = "http://api.themoviedb.org";
    private static final String POPULAR_MOVIES_PATH = "/3/movie/popular";
    private static final String API_KEY_PARAMETER = "api_key";
    private static final String TAG = MovieFetcher.class.getName();

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w500/";
    public static final String MOVIE_ARRAY_PARAMETER = "results";

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
                List<Movie> movies = null;

                try {
                    String url = getPopularMoviesURL();

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();

                    Response response = client.newCall(request).execute();
                    String body = response.body().string();

                    Log.d(TAG, "doInBackground: " + body);

                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Movie.class, new MovieDeserializer())
                            .create();

                    JsonObject jsonResponse = gson.fromJson(body, JsonObject.class);

                    JsonArray movieArray = jsonResponse.getAsJsonArray(MOVIE_ARRAY_PARAMETER);
                    movies = Arrays.asList(gson.fromJson(movieArray, Movie[].class));
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: ", e);
                }

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

    private String getPopularMoviesURL() throws IOException {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        builder.appendEncodedPath(POPULAR_MOVIES_PATH);
        builder.appendQueryParameter(API_KEY_PARAMETER, BuildConfig.MOVIES_DB_API_KEY);

        return builder.build().toString();
    }

    private class MovieDeserializer implements JsonDeserializer<Movie> {

        public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Movie movie = new Movie();

            JsonObject jsonObject = json.getAsJsonObject();

            movie.setPosterUrl(BASE_POSTER_URL + jsonObject.get("poster_path").getAsString());
            movie.setDescription(jsonObject.get("overview").getAsString());
            movie.setRating(jsonObject.get("vote_average").getAsBigDecimal());
            movie.setTitle(jsonObject.get("title").getAsString());

            return movie;
        }
    }
}
