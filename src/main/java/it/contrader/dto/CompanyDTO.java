package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "Company".
 */
public class CompanyDTO {
	
	private int id;
	private String name;
	private String address;
	private String city;
	private String sector;

	
	public CompanyDTO() {
		
	}

	public CompanyDTO (String name, String address, String city, String sector) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.sector = sector;
	}

	public CompanyDTO (int id, String name, String address, String city, String sector) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.sector = sector;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	@Override
	public String toString() {
		return  id + "\t"  + name +"\t\t" +   address + "\t\t" + city + "\t\t" + sector;
	}
}
