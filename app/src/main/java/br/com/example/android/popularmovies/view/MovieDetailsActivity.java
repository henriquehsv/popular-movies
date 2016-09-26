package br.com.example.android.popularmovies.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.databinding.ActivityMovieDetailsBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String MOVIE_EXTRA = "MOVIE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        MovieViewModel movieViewModel = new MovieViewModel();
        movieViewModel.setMovie(movie);

        viewDataBinding.setViewModel(movieViewModel);
    }
}
