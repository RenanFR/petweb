package com.scrum.challenge.model;

public enum Skills {
	
	
	JAVA("Java", "#f2f40b"),
	JAVASCRIPT("JavaScript", "#4dadf5"),
	PHP("PHP", "#4dadf5"),
	UI("UI Design", "#fdbdc6"),
	NODE("Node.js", "#14dcb4");
	
	private String description;
	private String color;
	
	Skills(String description, String color) {
		this.description = description;
		this.color = color;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getColor() {
		return color;
	}

}
