package br.com.example.android.popularmovies.model.data;

import java.math.BigDecimal;

public class Movie {
    private String posterUrl;
    private String title;
    private String description;
    private BigDecimal rating;

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
