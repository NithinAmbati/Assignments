import { Routes } from '@angular/router';
import { NewRequestsComponent } from './manager/new-requests/new-requests.component';
import { authGuard } from './protected/auth.guard';
import { OverviewComponent } from './manager/overview/overview.component';
import { AddEmployeeComponent } from './manager/add-employee/add-employee.component';
import { MyLeavesComponent } from './employee/my-leaves/my-leaves.component';
import { ApplyLeaveComponent } from './employee/apply-leave/apply-leave.component';
import { UserLoginComponent } from './auth/user-login/user-login.component';

export const routes: Routes = [
  // {
  //   path: '',
  //   redirectTo: 'login',
  //   pathMatch: 'full',
  // },
  {
    path: 'login',
    component: UserLoginComponent,
  },
  {
    path: 'manager',
    component: NewRequestsComponent,
    canActivate: [authGuard],
    data: { role: 'MANAGER' },
  },
  {
    path: 'manager/leaves-overview',
    component: OverviewComponent,
    canActivate: [authGuard],
    data: { role: 'MANAGER' },
  },
  {
    path: 'manager/add-employee',
    component: AddEmployeeComponent,
    canActivate: [authGuard],
    data: { role: 'MANAGER' },
  },
  {
    path: 'employee',
    component: MyLeavesComponent,
    canActivate: [authGuard],
    data: { role: 'EMPLOYEE' },
  },
  {
    path: 'employee/apply-leave',
    component: ApplyLeaveComponent,
    canActivate: [authGuard],
    data: { role: 'EMPLOYEE' },
  },
];
