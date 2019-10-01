package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "question")
	@NotNull
	private String question;

	@Column(name = "argument")
	@NotNull
	private String argument;

	@NotNull
	@Column(name = "sector")
	private String sector;

	@Nullable
	@Column(name = "recruiterId")
	@ManyToOne
	@JoinColumn(name = "id", table = "recruiter")
	private Integer recruiterId;
	
	@Nullable
	@Column(name = "companyId")
	@ManyToOne
	@JoinColumn(name = "id", table = "company")
	private Integer companyId;

}
