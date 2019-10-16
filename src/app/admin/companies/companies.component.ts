import { Component, OnInit } from '@angular/core';
import { CompanyDTO } from 'src/dto/companydto';
import { CompanyService } from 'src/service/company.service';
import { clearResolutionOfComponentResourcesQueue } from '@angular/core/src/metadata/resource_loading';
import { stringify } from '@angular/core/src/render3/util';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {
  companySectorOld: string;
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
    if (company.sector !== undefined) {
      this.service.update(company).subscribe(() => this.getCompanies());
      this.clear(company);
    }
  }

  insert(company: CompanyDTO) {
    this.service.insert(company).subscribe(() => this.getCompanies());
    this.clear(company);
  }

  clear(company){
    this.companytoinsert = new CompanyDTO();
    this.showSectorSelecter(company);
  }

  saveSector(sector) {
    this.companySectorOld=sector;
  }

  restoreSelecter(company) {
    company.id !== undefined ? company.sector = this.companySectorOld : company.sector = undefined;
    this.showSectorSelecter(company);
  }

  checkSectors(e, company) {
    if (e === '#') {
      this.showSectorInputText(company);
    }
  }
  showSectorInputText(company) {
    let id: string;
    company.id === undefined ? id = '' : id = company.id;
    const sectorSelecter = document.getElementById('sectorSelecter' + id);
    const sectorInputText = document.getElementById('sectorInputText' + id) as HTMLInputElement;
    sectorSelecter.style.display = 'none';
    company.sector = undefined;
    sectorInputText.required = true;
    sectorInputText.style.display = 'inline-block';
    sectorInputText.focus();
  }

  showSectorSelecter(company) {
    let id: string;
    company.id === undefined ? id = '' : id = company.id;
    const sectorSelecter = document.getElementById('sectorSelecter' + id);
    const sectorInputText = document.getElementById('sectorInputText' + id) as HTMLInputElement;
    sectorInputText.style.display = 'none';
    sectorInputText.required = false;
    sectorSelecter.style.display = 'inline-block';
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
