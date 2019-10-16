import { Component, OnInit } from '@angular/core';
import { QuestionDTO } from 'src/dto/questiondto';
import { RecruiterDTO } from 'src/dto/recruiterdto';
import { QuestionService } from 'src/service/question.service';
import { RecruiterService } from 'src/service/recruiter.service';
import { CompanyService } from 'src/service/company.service';
import { CompanyDTO } from 'src/dto/companydto';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})

export class QuestionsComponent implements OnInit {

  questions: QuestionDTO[];
  questionsOld: QuestionDTO[];
  recruiters: RecruiterDTO[];
  questiontoinsert: QuestionDTO = new QuestionDTO();
  questiontosearch: QuestionDTO = new QuestionDTO();
  companies: CompanyDTO[];
  sectorList: string[];

  constructor(private service: QuestionService, private recruiterService: RecruiterService,
              private companyService: CompanyService) { }

  ngOnInit() {
    this.getQuestions();
  }

  getQuestions() {
    this.service.getAll().subscribe(questions => this.questions = this.questionsOld = questions);
    this.recruiterService.getAll().subscribe(recruiters => this.recruiters = recruiters);
    this.companyService.getAll().subscribe(companies => {
      this.companies = companies;
      this.sectorList = Array.from(new Set(companies.map((item: CompanyDTO) => item.sector)));
    });
  }

  delete(question: QuestionDTO) {
    this.service.delete(question.id).subscribe(() => this.getQuestions());
  }

  update(question: QuestionDTO) {
    this.service.update(question).subscribe(() => this.getQuestions());
  }

  insert(question: QuestionDTO) {
    this.service.insert(question).subscribe(() => this.getQuestions());
    this.clear();
  }

  clear() {
    this.questiontoinsert = new QuestionDTO();
    (document.getElementById('companyField') as HTMLInputElement).value = '';
    (document.getElementById('sectorField') as HTMLInputElement).value = '';
  }

  setCompanyAndSector() {
    let recruiter = this.recruiters.find(r => r.id == this.questiontoinsert.recruiterId);
    (document.getElementById('companyField') as HTMLInputElement).value = recruiter.companyName;
    let company = this.companies.find(c => c.id == recruiter.companyId);
    (document.getElementById('sectorField') as HTMLInputElement).value = company.sector;
  }

  changeCompanyAndSector(question: QuestionDTO, id: number) {
    let companyNameField= (document.getElementById('companyName'+question.id) as HTMLInputElement);
    let companySectorField= (document.getElementById('companySector'+question.id) as HTMLInputElement);
    this.recruiters.forEach(r => {
      if (r.id == id) {
        question.recruiterId = r.id;
        companyNameField.value = r.companyName;
        let sector = this.companies.find(c => c.id == r.companyId).sector;
        companySectorField.value =sector;
      }
    });
  }

  setCompanyName(question): string {
    if (this.recruiters) {
      let recruiter = this.recruiters.find(r => r.id == question.recruiterId);
      question.companyName = recruiter.companyName;
      return question.companyName;
    }
    return '';
  }

  setCompanySector(question): string{
    if (this.recruiters && this.companies){
      let recruiter = this.recruiters.find(r => r.id == question.recruiterId);
      let company = this.companies.find(c => c.id == recruiter.companyId);
      question.sector = company.sector;
      return question.sector;
    }
    return '';
  }

  search() {
    this.questions = [];
    this.questionsOld.forEach(q => {
      if ((!this.questiontosearch.question || q.question.toLowerCase().includes(this.questiontosearch.question.toLowerCase()))
          && (!this.questiontosearch.topic || q.topic.toLowerCase().includes(this.questiontosearch.topic.toLowerCase()))
          && (!this.questiontosearch.recruiterId || q.recruiterId === this.questiontosearch.recruiterId)
          && (!this.questiontosearch.companyName || q.companyName === this.questiontosearch.companyName)
          && (!this.questiontosearch.sector || q.sector === this.questiontosearch.sector)) {
        this.questions.push(q);
      }
    });
  }

  clearSearch() {
    this.questiontosearch = new QuestionDTO();
    this.questions = this.questionsOld;
  }
}
