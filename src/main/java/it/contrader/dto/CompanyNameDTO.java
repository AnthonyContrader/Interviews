package it.contrader.dto;

public class CompanyNameDTO {
	private int id;
	private String name;
	
	
	public CompanyNameDTO () {
		
	}
	public CompanyNameDTO (int id, String name) {
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
