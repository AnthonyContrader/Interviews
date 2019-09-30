package it.contrader.model;

public class UserTypeDistinct {
	private String usertype;
	
	
	public UserTypeDistinct () {
		
	}
	public UserTypeDistinct (String usertype) {
		this.usertype = usertype;
	}
	
	public String getUsertype() {
		return this.usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
