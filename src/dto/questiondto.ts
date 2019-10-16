import { RecruiterDTO } from './recruiterdto';
import { CompanyDTO } from './companydto';

export class QuestionDTO {
    id: number;
    question: string;
    topic: string;
    recruiterId: number;
    recruiterName: string;
    companyName: string;
    sector: string;
}
