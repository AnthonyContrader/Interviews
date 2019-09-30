package it.contrader.dto;

public class UserRecruiterDTO {
	private int id;
	private String name;
	
	public UserRecruiterDTO () {
		
	}
	public UserRecruiterDTO (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
