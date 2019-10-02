package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyDTO {
	private Integer id;
	private String name;
	private String address;
	private String city;
	private String sector;
}