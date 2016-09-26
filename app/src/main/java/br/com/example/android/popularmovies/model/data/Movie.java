package br.com.example.android.popularmovies.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Movie implements Parcelable {
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

    //Parcelable boilerplate code

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterUrl);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeSerializable(this.rating);
    }

    public Movie() {}

    protected Movie(Parcel in) {
        this.posterUrl = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.rating = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {return new Movie(source);}

        @Override
        public Movie[] newArray(int size) {return new Movie[size];}
    };
}
