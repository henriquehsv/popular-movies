package br.com.example.android.popularmovies.data.networking;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.example.android.popularmovies.data.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoviesInfoFetcher {
    private static final String BASE_URL = "http://api.themoviedb.org";
    private static final String POPULAR_MOVIES_PATH = "/3/movie/popular";
    private static final String API_KEY_PARAMETER = "api_key";
    public static final String MOVIES_DB_KEY = "movies.db.key";
    private static final String TAG = MoviesInfoFetcher.class.getName();
    public static final String FILE_NAME_PROPERTIES = "local.properties";

    private static MoviesInfoFetcher instance;

    private MoviesInfoFetcher() {
    }

    public static MoviesInfoFetcher getInstance() {
        if (instance == null) {
            instance = new MoviesInfoFetcher();
        }

        return instance;
    }

    public void getPopularMovies(final OnMoviesFetchedListener onMoviesFetchedListener) {
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

                    movies = new ArrayList<>();
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: ", e);
                }

                return movies;
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                if (movies != null) {
                    onMoviesFetchedListener.onMoviesFetched(movies);
                } else {
                    onMoviesFetchedListener.onError();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private String getPopularMoviesURL() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_PROPERTIES);
        properties.load(inputStream);

        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        builder.appendEncodedPath(POPULAR_MOVIES_PATH);
        builder.appendQueryParameter(API_KEY_PARAMETER, properties.getProperty(MOVIES_DB_KEY));

        return builder.build().toString();
    }

    public interface OnMoviesFetchedListener {
        void onMoviesFetched(List<Movie> movies);

        void onError();
    }

}
