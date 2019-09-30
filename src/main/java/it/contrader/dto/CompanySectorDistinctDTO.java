package it.contrader.dto;

public class CompanySectorDistinctDTO {
	private String sector;
	
	
	public CompanySectorDistinctDTO () {
		
	}
	public CompanySectorDistinctDTO (String sector) {
		this.sector = sector;
	}
	
	public String getSector() {
		return this.sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
}
