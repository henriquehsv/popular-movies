package br.com.example.android.popularmovies.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.data.model.Movie;
import br.com.example.android.popularmovies.data.networking.MoviesInfoFetcher;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {
    private final Context context;
    private final Picasso picasso;
    private List<Movie> movies;

    public MoviePosterAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
        picasso = Picasso.with(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        picasso.load(MoviesInfoFetcher.BASE_POSTER_URL + movies.get(position).getPosterUrl())
               .into(holder.moviePoster);
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
        @BindView(R.id.moviePoster) ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
