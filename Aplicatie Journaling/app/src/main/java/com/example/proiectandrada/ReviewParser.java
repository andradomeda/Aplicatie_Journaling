package com.example.proiectandrada;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReviewParser {
    private static final String ID = "id";
    private static final String REVIEWTEXT = "reviewText";
    private static final String UTILIZATOR = "utilizator";
    private static final String RATING = "rating";

    public static List<Review> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareReviews(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Review> parsareReviews(JSONArray jsonArray) throws JSONException {
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            reviews.add(parsareReview(jsonArray.getJSONObject(i)));
        }
        return reviews;
    }

    private static Review parsareReview(JSONObject jsonObject) throws JSONException {


        Long id = jsonObject.has(ID) ? jsonObject.getLong(ID) : null;
        String reviewText = jsonObject.getString(REVIEWTEXT);
        String utilizator = jsonObject.getString(UTILIZATOR);
        Float rating = (float) jsonObject.getDouble(RATING);

        Review review = new Review(reviewText, utilizator, rating);
        review.setId(id);
        return review;

    }
}

