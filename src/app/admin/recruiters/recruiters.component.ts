import { Component, OnInit } from '@angular/core';
import { RecruiterDTO } from 'src/dto/recruiterdto';
import { RecruiterService } from 'src/service/recruiter.service';
import { CompanyDTO } from 'src/dto/companydto';
import { CompanyService } from 'src/service/company.service';

@Component({
  selector: 'app-recruiters',
  templateUrl: './recruiters.component.html',
  styleUrls: ['./recruiters.component.css']
})
export class RecruitersComponent implements OnInit {

  recruiters: RecruiterDTO[];
  companies: CompanyDTO[];
  recruitertoinsert: RecruiterDTO = new RecruiterDTO();

  constructor(private service: RecruiterService, private companyService: CompanyService) { }

  ngOnInit() {
    this.getRecruiters();
  }

  getRecruiters() {
    this.service.getAll().subscribe(recruiters => this.recruiters = recruiters);
    this.companyService.getAll().subscribe(companies => this.companies = companies);
  }

  delete(recruiter: RecruiterDTO) {
    this.service.delete(recruiter.id).subscribe(() => this.getRecruiters());
  }

  update(recruiter: RecruiterDTO) {
    this.service.update(recruiter).subscribe(() => this.getRecruiters());
  }

  insert(recruiter: RecruiterDTO) {
    this.service.insert(recruiter).subscribe(() => this.getRecruiters());
    this.clear();
  }

  clear() {
    this.recruitertoinsert = new RecruiterDTO();
  }

}
