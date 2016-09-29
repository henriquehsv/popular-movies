package br.com.example.android.popularmovies.viewmodel;

import android.databinding.BaseObservable;

public class MovieLayoutViewModel extends BaseObservable {
    private int textColor;
    private int backgroundColor;
    private int numberOfLines;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
