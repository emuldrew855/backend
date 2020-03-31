package com.ebay.queens.responses.searchitemresponse;

public class Seller {
	private String username;
	private String feedbackPercentage;
	private String feedbackScore;

	public String getFeedbackPercentage() {
		return feedbackPercentage;
	}

	public String getFeedbackScore() {
		return feedbackScore;
	}

	// Getters
	public String getUsername() {
		return username;
	}

	public void setFeedbackPercentage(String feedbackPercentage) {
		this.feedbackPercentage = feedbackPercentage;
	}

	public void setFeedbackScore(String feedbackScore) {
		this.feedbackScore = feedbackScore;
	}

	// Setters
	public void setUsername(String username) {
		this.username = username;
	}
}
