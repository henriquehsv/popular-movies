package br.com.example.android.popularmovies.model.networking;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieFetcherProvider {

    @Provides
    public MovieFetcher getMovieFetcher() {
        return new MockMovieFetcher();
    }
}
