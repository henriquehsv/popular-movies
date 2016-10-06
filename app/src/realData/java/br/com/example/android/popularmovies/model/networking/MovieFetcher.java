package br.com.example.android.popularmovies.model.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import br.com.example.android.popularmovies.BuildConfig;
import br.com.example.android.popularmovies.model.data.Movie;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MovieFetcher {
    private static final String BASE_URL = "http://api.themoviedb.org";

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

    public void fetchMovies(final Observer<List<Movie>> observer) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());

        builder.baseUrl(BASE_URL);
        MoviesDBAPI moviesDBAPI = builder.build().create(MoviesDBAPI.class);
        Observable<JsonObject> jsonObservable = moviesDBAPI.popularMovies(BuildConfig.MOVIES_DB_API_KEY);
        jsonObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<JsonObject, List<Movie>>() {
            @Override
            public List<Movie> call(JsonObject jsonObject) {
                Gson gson = new GsonBuilder().registerTypeAdapter(Movie.class, new MovieDeserializer()).create();
                JsonArray movieArray = jsonObject.getAsJsonArray(MOVIE_ARRAY_PARAMETER);

                return Arrays.asList(gson.fromJson(movieArray, Movie[].class));
            }
        }).subscribe(observer);
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
