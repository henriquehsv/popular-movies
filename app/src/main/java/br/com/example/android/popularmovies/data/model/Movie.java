package br.com.example.android.popularmovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Movie {
    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("vote_average")
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
}
