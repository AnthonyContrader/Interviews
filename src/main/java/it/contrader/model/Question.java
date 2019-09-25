package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Question {

	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private int id;
	private String question;
	private String sector;
	private String recruiter;
	private String company;
	private int recruiterid;
	private int companyid;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo User
	 */
	public Question() {
		
	}

	public Question (String question, String sector, int recruiterid, int companyid,String recruiter, String company) {
		this.question = question;
		this.sector = sector;
		this.recruiterid = recruiterid;
		this.companyid = companyid;
		this.recruiter = recruiter;
		this.company = company;
	}

	public Question (int id, String question, String sector, int recruiterid, int companyid) {
		this.id = id;
		this.question = question;
		this.sector = sector;
		this.recruiterid = recruiterid;
		this.companyid = companyid;
	}
	
	

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di User
	 */
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	public String getRecruiter() {
		return this.recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setRecruiterid(int recruiterid) {
		this.recruiterid = recruiterid;
	}

	public int getRecruiterid() {
		return recruiterid;
	}
	
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getCompanyid() {
		return companyid;
	}

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + question +"\t\t" +   sector + "\t\t" + recruiterid + "\t\t" + companyid;
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (sector == null) {
			if (other.sector != null)
				return false;
		} else if (!sector.equals(other.sector))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (recruiter == null) {
			if (other.recruiter != null)
				return false;
		} else if (!recruiter.equals(other.recruiter))
			return false;
		if (recruiterid == 0) {
			if (other.recruiterid != 0)
				return false;
		} else if (recruiterid != other.recruiterid)
			return false;
		if (companyid == 0) {
			if (other.companyid != 0)
				return false;
		} else if (companyid != other.companyid)
			return false;
		return true;
	}
}
