package br.com.example.android.popularmovies.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.databinding.MovieItemBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.viewmodel.MovieLayoutViewModel;
import br.com.example.android.popularmovies.viewmodel.MovieViewModel;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {
    private final MovieLayoutViewModel movieLayoutViewModel;
    private List<Movie> movies;

    public MoviePosterAdapter(Context context) {
        movies = Collections.emptyList();
        movieLayoutViewModel = new MovieLayoutViewModel();
        movieLayoutViewModel.setNumberOfLines(2);

        int textColor = context.getResources().getColor(R.color.movieDescriptionColor);
        movieLayoutViewModel.setTextColor(textColor);

        int background = context.getResources().getColor(R.color.movieBackgroundColor);
        movieLayoutViewModel.setBackgroundColor(background);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //The name of this class is based on the layout name
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_item,
                parent,
                false);

        return new ViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieItemBinding movieItemBinding = holder.binding;

        MovieViewModel movieViewModel = movieItemBinding.getViewModel();

        if (movieViewModel == null) {
            movieViewModel = new MovieViewModel();
        }

        movieViewModel.setMovie(movies.get(position));
        movieItemBinding.setViewModel(movieViewModel);
        movieItemBinding.setLayoutViewModel(movieLayoutViewModel);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding binding;

        public ViewHolder(MovieItemBinding binding) {
            super(binding.cardView);

            this.binding = binding;
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
