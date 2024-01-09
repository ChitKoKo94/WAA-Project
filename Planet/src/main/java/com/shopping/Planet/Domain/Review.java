package com.shopping.Planet.Domain;

public class Review {
    private String review;
    private int rating;
    private User user;

    public Review(String review, int rating, User user) {
        this.review = review;
        this.rating = rating;
        this.user = user;
    }

    public Review() {}

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
