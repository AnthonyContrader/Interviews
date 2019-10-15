/**
 * Classe DTO di User. DEVE essere uguale (stesso nome classe, stessi attributi e stessi nomi) a
 * quello nel backend. 
 * 
 * @see Usertype
 * 
 * @author Vittorio Valent
 */
export class UserDTO {

   id: number;

   login: string;

   firstName: string;

   lastName: string;

   password: string;

   authorities: string[];

   email: string;
}

