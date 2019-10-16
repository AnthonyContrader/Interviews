import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { QuestionsComponent } from './questions/questions.component';

const routes: Routes = [
  { path: 'user-dashboard', component: UserLayoutComponent, children: [
    { path: '', component: UserDashboardComponent},
    { path: 'questions', component: QuestionsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
