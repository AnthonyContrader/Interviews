package it.contrader.service;

import java.util.List;

import it.contrader.converter.CompanyConverter;
import it.contrader.dao.CompanyDAO;
import it.contrader.dto.CompanyDTO;

/**
 * 
 * @author Vittorio
 *
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class CompanyService {
	private CompanyConverter companyConverter;
	private CompanyDAO companyDAO;

	public CompanyService(){
		this.companyDAO = new CompanyDAO();
		this.companyConverter = new CompanyConverter();
	}
	
	public List<CompanyDTO> getAll() {
		return this.companyConverter.toDTOList(this.companyDAO.getAll());
	}

	// chiama il metodo del DAO che restituisce uno company in base al suo id
	public CompanyDTO read(int id) {
		return this.companyConverter.toDTO(this.companyDAO.read(id));
	}

	// chiama il metodo del DAO che cancella uno company in base al suo id
	public boolean delete(int companyId) {
		return companyDAO.delete(companyId);
	}

	// chiama il metodo del DAO che inserisce un oggetto Company
	public boolean insert(CompanyDTO companyDTO) {
		return companyDAO.insert(companyConverter.toEntity(companyDTO));
	}

	// chiama il metodo del DAO che modifica un utente
	public boolean update(CompanyDTO companyDTO) {
		return companyDAO.update(companyConverter.toEntity(companyDTO));
	}
}
