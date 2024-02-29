package com.example.onlineshopping.AdaptersAndModels;

public class FeedbackModel {



    String username;
    String Feedback;
    public FeedbackModel( String Feedback , String username) {

        this.Feedback = Feedback;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFeedback() {
        return Feedback;
    }

}
