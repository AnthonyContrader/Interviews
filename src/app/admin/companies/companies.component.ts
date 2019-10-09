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
  companytoinsert: CompanyDTO = new CompanyDTO();

  constructor(private service: CompanyService) { }

  ngOnInit() {
    this.getCompanies();
  }

  getCompanies() {
    this.service.getAll().subscribe(companies => this.companies = companies);
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
    sectorSelecter.style.display='none';
    this.companytoinsert.sector = undefined;
    sectorInputText.required = true;
    sectorInputText.style.display='block';
  }

  showSectorSelecter() {
    const sectorSelecter = document.getElementById('sectorSelecter');
    const sectorInputText = document.getElementById('sectorInputText') as HTMLInputElement;
    sectorInputText.style.display = 'none';
    sectorInputText.required = false;
    sectorSelecter.style.display = 'block';
  }

}
