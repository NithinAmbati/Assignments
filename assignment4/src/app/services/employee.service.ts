import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { mainUrl } from '../store/data';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  headers: any = '';

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private authService: AuthService
  ) {
    this.headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: `${
        this.authService.getUserRole() === 'EMPLOYEE' ? 'employee' : 'manager'
      }Token`,
    });
  }

  getLeavesByEmployee(employeeId: string): Observable<any> {
    return this.http.get(
      `${mainUrl}/api/employee/get-all-leaves-by-employee/${employeeId}`,
      {
        headers: this.headers,
      }
    );
  }

  applyLeave(leaveApplication: {
    reason: string;
    leaveType: string;
    startDate: Date;
    endDate: Date;
  }): Observable<any> {
    const stringifiedJwtToken = this.cookieService.get('jwtToken');
    if (stringifiedJwtToken) {
      const jwtToken = JSON.parse(this.cookieService.get('jwtToken'));
      const employeeId = jwtToken[1];
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'employeeToken',
      });
      return this.http.post(
        `${mainUrl}/api/employee/apply-leave/${employeeId}`,
        leaveApplication,
        { headers }
      );
    }

    return this.http.post(
      `${mainUrl}/api/employee/apply-leave/${null}`,
      leaveApplication
    );
  }
}
