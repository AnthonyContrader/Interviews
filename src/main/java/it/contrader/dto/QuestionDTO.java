package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

	private Integer id;

	private String question;
	
	private String argument;

	private String sector;
	
	private Integer recruiterId;
	
	private Integer companyId;
}
