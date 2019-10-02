package it.contrader.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
	@Id
	@Column(name="id")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name="name")
	@NotNull
	private String name;
	
	@Column (name= "address")
	@Nullable
	private String address;
	
	@Column (name="city")
	@Nullable
	private String city;
	
	@Column (name="sector")
	@NotNull
	private String sector;
}