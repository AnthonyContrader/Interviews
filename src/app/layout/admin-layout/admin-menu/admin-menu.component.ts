import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  isUserCollapsed = false;
  isClientCollapsed = false;
  isRecruiterCollapsed = false;
  isCompanyCollapsed = false;
  isQuestionCollapsed = false;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  userscollapse() {
    if (this.isUserCollapsed === false) {
      this.isUserCollapsed = true;
    } else { this.isUserCollapsed = false; }
  }

  recruiterscollapse() {
    if (this.isRecruiterCollapsed === false) {
      this.isRecruiterCollapsed = true;
    } else { this.isRecruiterCollapsed = false; }
  }

  companiescollapse() {
    if (this.isCompanyCollapsed === false) {
      this.isCompanyCollapsed = true;
    } else { this.isCompanyCollapsed = false; }
  }

  questionscollapse() {
    if (this.isQuestionCollapsed === false) {
      this.isQuestionCollapsed = true;
    } else { this.isQuestionCollapsed = false; }
  }
}
