package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO � simile al Model ma pu� contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "User".
 */
public class UserDTO {
	
	private int id;
	private String username;
	private String password;
	private String usertype;
	private int companyid;	
	private String company;

	
	public UserDTO() {
		
	}

	public UserDTO (String username, String password, String usertype, int companyid, String company) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.company = company;
		this.companyid = companyid;
	}
	
	public UserDTO (int id, String username, String password, String usertype, int companyid, String company) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.company = company;
		this.companyid = companyid;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}
	
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getCompanyid() {
		return companyid;
	}

	@Override
	public String toString() {
		return  id + "\t"  + username +"\t\t" +   password + "\t\t" + usertype;
	}
}
