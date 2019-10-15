import { Component, OnInit } from '@angular/core';
import { CompanyDTO } from 'src/dto/companydto';
import { CompanyService } from 'src/service/company.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  companies: CompanyDTO[];
  companiesOld: CompanyDTO[];
  companytoinsert: CompanyDTO = new CompanyDTO();
  companytosearch: CompanyDTO = new CompanyDTO();
  sectorList: string[];

  constructor(private service: CompanyService) { }

  ngOnInit() {
    this.getCompanies();
  }

  getCompanies() {
    this.service.getAll().subscribe(companies => {
      this.companies = this.companiesOld = companies;
      this.sectorList = Array.from(new Set(companies.map((item: CompanyDTO) => item.sector)));
    });
  }

  delete(company: CompanyDTO) {
    this.service.delete(company.id).subscribe(() => this.getCompanies());
  }

  update(company: CompanyDTO) {
    this.service.update(company).subscribe(() => this.getCompanies());
  }

  insert(company: CompanyDTO) {
    this.service.insert(company).subscribe(() => this.getCompanies());
    this.clear();
  }

  clear(){
    this.companytoinsert = new CompanyDTO();
    this.showSectorSelecter();
  }

  checkSectors(e) {
    if (e === '#') {
      this.showSectorInputText();
    } else {
      this.showSectorSelecter();
    }
  }

  showSectorInputText() {
    const sectorSelecter = document.getElementById('sectorSelecter');
    const sectorInputText = document.getElementById('sectorInputText') as HTMLInputElement;
    sectorSelecter.style.display = 'none';
    this.companytoinsert.sector = undefined;
    sectorInputText.required = true;
    sectorInputText.style.display = 'block';
  }

  showSectorSelecter() {
    const sectorSelecter = document.getElementById('sectorSelecter');
    const sectorInputText = document.getElementById('sectorInputText') as HTMLInputElement;
    sectorInputText.style.display = 'none';
    sectorInputText.required = false;
    sectorSelecter.style.display = 'block';
  }

  search() {
    this.companies = [];
    this.companiesOld.forEach(c => {
      if ((!this.companytosearch.name || c.name.toLowerCase().includes(this.companytosearch.name.toLowerCase()))
          && (!this.companytosearch.address || c.address.toLowerCase().includes(this.companytosearch.address.toLowerCase()))
          && (!this.companytosearch.city || c.city.toLowerCase().includes(this.companytosearch.city.toLowerCase()))
          && (!this.companytosearch.sector || c.sector.includes(this.companytosearch.sector))) {
        this.companies.push(c);
      }
    });
  }

  clearSearch() {
    this.companytosearch = new CompanyDTO();
    this.companies = this.companiesOld;
  }
}
