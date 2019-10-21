package it.contrader.interviews.web.rest.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.contrader.interviews.service.dto.CompanyDTO;

@FeignClient(name = "interviews2")
public interface RecruiterFeignClient {
    
	@GetMapping("/api/companies")
	public List<CompanyDTO> getAllCompanies();
	
	@GetMapping(value="/api/companies/{id}")
	public CompanyDTO getCompany(@PathVariable(value="id") Long id);
 
}
