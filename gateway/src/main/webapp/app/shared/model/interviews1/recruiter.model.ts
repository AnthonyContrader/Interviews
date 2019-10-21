export interface IRecruiter {
    id?: number;
    name?: string;
    companyId?: number;
    companyName?: string;
}

export class Recruiter implements IRecruiter {
    constructor(public id?: number, public name?: string, public companyId?: number, public companyName?: string) {}
}
