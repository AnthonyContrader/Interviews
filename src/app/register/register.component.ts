import { Component, OnInit } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  angForm: FormGroup;
  submitted = false;

  constructor(private router: Router, private fb: FormBuilder, private service: UserService) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.angForm = this.fb.group({
       login: ['', Validators.required ],
       password: ['', Validators.required ],
       firstName: ['', Validators.required ],
       lastName: ['', Validators.required ],
       email: ['', Validators.required ],
    });
  }

  signUp() {
    this.submitted = true;
    if (this.angForm.valid) {
      this.service.insert(this.angForm.value).subscribe(() => this.router.navigateByUrl('/login'));
      this.angForm.reset();
      this.submitted = false;
    }
  }

  isInvalid(field: string): boolean {
    return (this.submitted && (this.angForm.controls[field].invalid))
            || this.angForm.controls[field].invalid && (this.angForm.controls[field].dirty
              || this.angForm.controls[field].touched);
  }

}
