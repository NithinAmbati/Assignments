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
      Authorization: 'employeeToken',
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

      return this.http.post(
        `${mainUrl}/api/employee/apply-leave/${employeeId}`,
        leaveApplication,
        { headers: this.headers }
      );
    }

    return this.http.post(
      `${mainUrl}/api/employee/apply-leave/${null}`,
      leaveApplication
    );
  }

  editLeave(leaveApplication: any): Observable<any> {
    return this.http.put(
      `${mainUrl}/api/employee/edit-leave/${leaveApplication.id}`,
      leaveApplication,
      {
        headers: this.headers,
      }
    );
  }

  deleteLeave(leaveId: string): Observable<any> {
    return this.http.delete(`${mainUrl}/api/employee/delete-leave/${leaveId}`, {
      headers: this.headers,
    });
  }
}
