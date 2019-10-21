export interface IQuestion {
    id?: number;
    question?: string;
    topic?: string;
    recruiterName?: string;
    recruiterId?: number;
}

export class Question implements IQuestion {
    constructor(
        public id?: number,
        public question?: string,
        public topic?: string,
        public recruiterName?: string,
        public recruiterId?: number
    ) {}
}
