package edu.neu.info6205.algorithms.Christofides;

public class Location {
	private int id;
	private String crimeId;
	private double longitude;
	private double latitude;
	
	public Location(int id, String crimeId, double longitude, double latitude) {
		this.id = id;
		this.crimeId = crimeId;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(String crimeId) {
		this.crimeId = crimeId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
