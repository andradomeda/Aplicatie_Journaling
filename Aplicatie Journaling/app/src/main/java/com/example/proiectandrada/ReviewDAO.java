package com.example.proiectandrada;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Insert
    void insertReview(Review review);

    @Query("SELECT * FROM reviews")
    List<Review> getReviews();
    @Query("SELECT * FROM reviews WHERE rating >= :minRating")
    List<Review> getReviewsByRating(float minRating);

    @Update
    void updateReview(Review review);

    @Query("DELETE FROM reviews WHERE utilizator = :username")
    void deleteReviewsByUser(String username);
}
