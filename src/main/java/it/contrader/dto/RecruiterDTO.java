package it.contrader.dto;

import it.contrader.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterDTO {
	private Integer id;
	private String name;
	private Company company;
}
