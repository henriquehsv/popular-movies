package br.com.example.android.popularmovies.model.networking;

import android.os.AsyncTask;

import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.data.OnMoviesFetchedListener;

public class MovieFetcher {
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
                return MockMovieData.getMovies();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                moviesFetchedListener.onMoviesFetched(movies);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
