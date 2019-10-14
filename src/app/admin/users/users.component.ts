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
    this.service.delete(user.id).subscribe(() => this.getUsers());
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.getUsers());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
    this.clear();
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }

  showPassword(e) {
    e.target.value = '';
    e.target.type = 'text';
  }

  search() {
    this.users = [];
    this.usersOld.forEach(u => {
      if ((!this.usertosearch.username || u.username.toLowerCase().includes(this.usertosearch.username.toLowerCase()))
          && (!this.usertosearch.userType || u.userType == this.usertosearch.userType)
          && (!this.usertosearch.email || u.email.toLowerCase().includes(this.usertosearch.email.toLowerCase()))) {
        this.users.push(u);
      }
    });
  }

  clearSearch() {
    this.usertosearch = new UserDTO();
    this.users = this.usersOld;
  }

}
