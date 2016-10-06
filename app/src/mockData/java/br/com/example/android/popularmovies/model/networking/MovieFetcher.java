package br.com.example.android.popularmovies.model.networking;

import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieFetcher {
    private static MovieFetcher instance;

    private MovieFetcher() {}

    public static MovieFetcher getInstance() {
        if (instance == null) {
            instance = new MovieFetcher();
        }

        return instance;
    }

    public void fetchMovies(Observer<List<Movie>> observer) {
        Observable.just(MockMovieData.getMovies())
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);
    }
}
