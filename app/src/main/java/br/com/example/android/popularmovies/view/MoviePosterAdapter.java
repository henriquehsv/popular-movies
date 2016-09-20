package br.com.example.android.popularmovies.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.databinding.MovieItemBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.viewmodel.MovieViewModel;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {
    private final Context context;
    private List<Movie> movies;

    public MoviePosterAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //The name of this variable is based on the layout name
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);

        return new ViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieItemBinding movieItemBinding = holder.binding;
        movieItemBinding.setMovie(new MovieViewModel(movies.get(position)));
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
