package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Company {
	/**
	 * Qui sotto definisco gli attributi di Company. 
	 */
	private int id;

	private String name;
	
	private String address;
	
	private String city;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Company
	 */
	public Company() {
		
	}

	public Company (String name, String address, String city) {
		this.name = name;
		this.address = address;
		this.city = city;
	}

	public Company (int id, String name, String address, String city) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di Company
	 */
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

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + name +"\t\t" +   address + "\t\t" + city;
	}

	//Metodo per il confronto degli oggetti
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name!= null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}
}
