package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "User".
 */
public class QuestionDTO {
	
	private int id;
	private String question;
	private String sector;
	private String recruiter;
	private String company;
	private int recruiterid;
	private int companyid;

	
	public QuestionDTO() {
		
	}

	public QuestionDTO (String question, String sector, int recruiterid, int companyid) {
		this.question = question;
		this.sector = sector;
		this.recruiterid = recruiterid;
		this.companyid = companyid;
	}

	public QuestionDTO (int id, String question, String sector, int recruiterid, int companyid) {
		this.question = question;
		this.sector = sector;
		this.recruiterid = recruiterid;
		this.companyid = companyid;
	}
	
	public QuestionDTO (String question, String sector, String recruiter, String company) {
		this.question = question;
		this.sector = sector;
		this.recruiter = recruiter;
		this.company = company;
	}

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

	public void setRecruiterid(int recruiterid) {
		this.recruiterid = recruiterid;
	}
	
	public int getRecruiterid() {
		return recruiterid;
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
	
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getCompanyid() {
		return companyid;
	}

	@Override
	public String toString() {
		return  id + "\t"  + question +"\t\t" +   sector + "\t\t" + recruiterid + "\t\t" + companyid;
	}
}
