export interface ICompany {
    id?: number;
    name?: string;
    address?: string;
    city?: string;
    sector?: string;
}

export class Company implements ICompany {
    constructor(public id?: number, public name?: string, public address?: string, public city?: string, public sector?: string) {}
}
