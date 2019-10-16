import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserDTO[];
  usersOld: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();
  usertosearch: UserDTO = new UserDTO();

  constructor(private service: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.users = this.usersOld = users);
  }

  delete(user: UserDTO) {
    this.service.deleteUser(user.login).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    let e = document.getElementById('usertype_' + user.login) as HTMLSelectElement;
    user.authorities = [e.options[e.selectedIndex].value];
    this.service.update(user).subscribe(() => this.getUsers());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
    this.clear();
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }

  search() {
    this.users = [];
    this.usersOld.forEach(u => {
      if ((!this.usertosearch.login || u.login.toLowerCase().includes(this.usertosearch.login.toLowerCase()))
          && (!this.usertosearch.firstName || u.firstName.toLowerCase().includes(this.usertosearch.firstName.toLowerCase()))
          && (!this.usertosearch.lastName || u.lastName.toLowerCase().includes(this.usertosearch.lastName.toLowerCase()))
          && (!this.usertosearch.authorities || u.authorities.some(a => this.usertosearch.authorities.includes(a)))
          && (!this.usertosearch.email || u.email.toLowerCase().includes(this.usertosearch.email.toLowerCase()))
          && (this.usertosearch.activated === undefined || u.activated == this.usertosearch.activated)) {
        this.users.push(u);
      }
    });
  }

  clearSearch() {
    this.usertosearch = new UserDTO();
    this.users = this.usersOld;
  }

}
