package it.contrader.model;

public class CompanySectorDistinct {
	private String sector;
	
	
	public CompanySectorDistinct () {
		
	}
	public CompanySectorDistinct (String sector) {
		this.sector = sector;
	}
	
	public String getSector() {
		return this.sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
}
