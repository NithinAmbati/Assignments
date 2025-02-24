import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { ManagerService } from '../../services/manager.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user-login',
  imports: [],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css',
})
export class UserLoginComponent {
  constructor(
    private employeeService: EmployeeService,
    private managerService: ManagerService,
    private cookieService: CookieService,
    private authService: AuthService,
    private router: Router
  ) {
    if (authService.isAuthenticated()) {
      const userRole = authService.getUserRole();
      if (userRole === 'MANAGER') router.navigate(['/manager']);
      else if (userRole === 'EMPLOYEE') router.navigate(['/employee']);
    }
  }

  email: string = '';
  password: string = '';
  role: string = 'EMPLOYEE';
  showPassword = false;

  onChangeEmail(event: Event): void {
    this.email = (event.target as HTMLInputElement).value;
  }

  onChangePassword(event: Event): void {
    this.password = (event.target as HTMLInputElement).value;
  }

  onChangeRole(event: Event): void {
    this.role = (event.target as HTMLInputElement).value;
  }

  onChangeShowPassword(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(event: Event): void {
    event.preventDefault();
    this.authService.login(this.email, this.password, this.role);
  }

  ngOnInit(): void {}
}
