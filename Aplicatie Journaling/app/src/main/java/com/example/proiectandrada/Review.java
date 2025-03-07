package com.example.proiectandrada;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Reviews")
public class Review implements Serializable {
    private String reviewText;
    private String utilizator;
    private Float rating;
    @PrimaryKey(autoGenerate = true)
    private Long id;
    public Review(String reviewText, String utilizator, Float rating) {
        this.reviewText = reviewText;
        this.utilizator = utilizator;
        this.rating = rating;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +'\'' +
                "reviewText='" + reviewText + '\'' +
                ", utilizator='" + utilizator + '\'' +
                ", rating=" + rating +
                '}';
    }
}
