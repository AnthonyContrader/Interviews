import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
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
  }

  setCompanyAndSector() {
    (document.getElementById('companyField') as HTMLInputElement).value = this.questiontoinsert.recruiter.company.name;
    (document.getElementById('sectorField') as HTMLInputElement).value = this.questiontoinsert.recruiter.company.sector;
    this.questiontoinsert.company = this.questiontoinsert.recruiter.company;
    this.questiontoinsert.sector = this.questiontoinsert.recruiter.company.sector;
  }

  changeCompanyAndSector(question: QuestionDTO, id: number) {
    this.recruiters.forEach(r => {
      if (r.id == id) {
        question.recruiter = r;
        question.company = r.company;
        question.sector = r.company.sector;
      }
    });
  }

  search() {
    this.questions = [];
    this.questionsOld.forEach(q => {
      if ((!this.questiontosearch.question || q.question.toLowerCase().includes(this.questiontosearch.question.toLowerCase()))
          && (!this.questiontosearch.topic || q.topic.toLowerCase().includes(this.questiontosearch.topic.toLowerCase()))
          && (!this.questiontosearch.recruiter || q.recruiter.id == this.questiontosearch.recruiter.id)
          && (!this.questiontosearch.company || q.company.id == this.questiontosearch.company.id)
          && (!this.questiontosearch.sector || q.sector.includes(this.questiontosearch.sector))) {
        this.questions.push(q);
      }
    });
  }

  clearSearch() {
    this.questiontosearch = new QuestionDTO();
    this.questions = this.questionsOld;
  }
}
