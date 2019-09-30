package it.contrader.dto;

public class UserTypeDistinctDTO {
	private String usertype;
	
	
	public UserTypeDistinctDTO () {
		
	}
	public UserTypeDistinctDTO (String usertype) {
		this.usertype = usertype;
	}
	
	public String getUsertype() {
		return this.usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
