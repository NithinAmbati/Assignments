import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { EmployeeService } from './employee.service';
import { ManagerService } from './manager.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { mainUrl } from '../store/data';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  userRole: string = '';
  userId: string = '';
  username: string = '';

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) {
    this.initializeAuth();
  }

  loginEmployee(userDetails: {
    email: string;
    password: string;
  }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    console.log(userDetails);
    return this.http.post(
      `${mainUrl}/login/employee`,
      JSON.stringify(userDetails),
      {
        headers,
      }
    );
  }

  loginManager(userDetails: {
    email: string;
    password: string;
  }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'managerToken',
    });
    console.log(userDetails);
    return this.http.post(
      `${mainUrl}/login/manager`,
      JSON.stringify(userDetails),
      {
        headers,
      }
    );
  }

  login(email: string, password: string, role: string): void {
    if (role === 'EMPLOYEE') {
      this.loginEmployee({
        email: email,
        password: password,
      }).subscribe({
        next: (res) => {
          const jwtToken = [
            res.jwtToken.role,
            res.jwtToken.id,
            res.jwtToken.username,
          ];

          this.cookieService.set('jwtToken', JSON.stringify(jwtToken), 1, '/');
          this.initializeAuth();
          alert(res.message);
          this.router.navigate(['/employee']);
        },
        error: (error) => alert(error.error.message),
      });
    } else {
      this.loginManager({
        email: email,
        password: password,
      }).subscribe({
        next: (res) => {
          const jwtToken = [
            res.jwtToken.role,
            res.jwtToken.id,
            res.jwtToken.username,
          ];
          this.cookieService.set('jwtToken', JSON.stringify(jwtToken), 1, '/');
          this.initializeAuth();
          alert(res.message);
          this.router.navigate(['/manager']);
        },
        error: (error) => alert(error.error.message),
      });
    }
  }

  logout(): void {
    console.log(JSON.parse(this.cookieService.get('jwtToken')));
    this.cookieService.delete('jwtToken');
    this.initializeAuth();
    this.router.navigate(['/login']);
  }

  initializeAuth(): void {
    const stringifiedJwtToken = this.cookieService.get('jwtToken');
    if (stringifiedJwtToken) {
      const jwtToken = JSON.parse(this.cookieService.get('jwtToken'));
      this.userRole = jwtToken[0];
      this.userId = jwtToken[1];
      this.username = jwtToken[2];
    } else {
      this.userRole = '';
      this.userId = '';
      this.username = '';
    }
  }

  isAuthenticated(): boolean {
    if (this.userRole === 'MANAGER' || this.userRole === 'EMPLOYEE')
      return true;
    return false;
  }

  getUserRole(): string {
    return this.userRole;
  }

  getUserId(): string {
    return this.userId;
  }

  getUsername(): string {
    return this.username;
  }
}
