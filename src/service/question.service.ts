import { Injectable } from '@angular/core';
import { QuestionDTO } from 'src/dto/questiondto';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuestionService extends AbstractService<QuestionDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.type = 'interviews1/api/questions';
  }
}
