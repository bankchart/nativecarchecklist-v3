package com.example.nativecarchecklist;

public class RatingCheckList {
	private int exteriorPriority;
	private int interiorPriority;
	private int powerPriority;
	private int enginePriority;
	private int documentPriority;
	private int rating;

	public void setPriority(int exterior, int interior, int power, int engine,
			int document) {
		exteriorPriority = exterior;
		interiorPriority = interior;
		powerPriority = power;
		enginePriority = engine;
		documentPriority = document;
	}

	public double getRating(double exterior, double interior, double power,
			double engine, double document) {

		double weightSum = exteriorPriority + interiorPriority + powerPriority
				+ enginePriority + documentPriority;

		double ratingTmp = ((double) exteriorPriority * exterior
				+ (double) interiorPriority * interior + (double) powerPriority
				* power + (double) enginePriority * engine + (double) documentPriority
				* document)
				/ weightSum;
		return ratingTmp;
	}

	public void setExterior(int exterior) {
		this.exteriorPriority = exterior;
	}

	public int getExterior() {
		return exteriorPriority;
	}

	public void setInterior(int interior) {
		this.interiorPriority = interior;
	}

	public int getInterior() {
		return interiorPriority;
	}

	public void setPower(int power) {
		this.powerPriority = power;
	}

	public int getPower() {
		return powerPriority;
	}

	public void setEngine(int engine) {
		this.enginePriority = engine;
	}

	public int getEngine() {
		return enginePriority;
	}

	public void setDocument(int document) {
		this.documentPriority = document;
	}

	public int getDocument() {
		return documentPriority;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}
}
