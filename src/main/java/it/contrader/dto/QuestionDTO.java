package it.contrader.dto;

import it.contrader.model.Company;
import it.contrader.model.Recruiter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
	private long id;
	private String question;
	private String topic;
	private String sector;
	private Recruiter recruiter;
	private Company company;
}
