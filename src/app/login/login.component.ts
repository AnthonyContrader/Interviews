import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);

    this.service.login(this.loginDTO).subscribe(token => {
      if (token != null) {
        const tk = token.id_token;
        localStorage.setItem('id_token', tk);
        this.service.getAccount().subscribe(account => {
          localStorage.setItem('currentUser', JSON.stringify(account));
          if (account.authorities.includes('ROLE_ADMIN')) {
            this.router.navigate(['/admin-dashboard']);
          } else if (account.authorities.includes('ROLE_USER')) {
            this.router.navigate(['/user-dashboard']);
          } else {
            this.router.navigate(['/login']);
          }
        });
      }
    });
  }
}
