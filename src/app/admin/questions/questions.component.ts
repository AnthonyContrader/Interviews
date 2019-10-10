import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { QuestionDTO } from 'src/dto/questiondto';
import { RecruiterDTO } from 'src/dto/recruiterdto';
import { CompanyDTO } from 'src/dto/companydto';
import { QuestionService } from 'src/service/question.service';
import { RecruiterService } from 'src/service/recruiter.service';
import { CompanyService } from 'src/service/company.service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questions: QuestionDTO[];
  recruiters: RecruiterDTO[];
  questiontoinsert: QuestionDTO = new QuestionDTO();

  constructor(private service: QuestionService, private recruiterService: RecruiterService) { }

  ngOnInit() {
    this.getQuestions();
  }

  getQuestions() {
    this.service.getAll().subscribe(questions => this.questions = questions);
    this.recruiterService.getAll().subscribe(recruiters => this.recruiters = recruiters);
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

  changeCompanyAndSector(question: QuestionDTO, recruiterId: string) {
    const id = parseInt(recruiterId.split(':')[1].trim(), 10 );
    this.recruiters.forEach(x => {
      if (x.id == id) {
        question.recruiter = x;
        question.company = question.recruiter.company;
        question.sector = question.recruiter.company.sector;
      }
    });
  }
}
