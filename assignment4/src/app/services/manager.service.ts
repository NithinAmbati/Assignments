import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { mainUrl } from '../store/data';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ManagerService {
  headers: any = '';
  constructor(private http: HttpClient, private authService: AuthService) {
    this.headers = new HttpHeaders({
      'Content-type': 'application/json',
      Authorization: `${
        this.authService.getUserRole() === 'EMPLOYEE' ? 'employee' : 'manager'
      }Token`,
    });
  }

  addEmployee(userDetails: {
    username: string;
    password: string;
    email: string;
  }): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'managerToken',
    });
    return this.http.post(
      `${mainUrl}/api/manager/add-employee`,
      JSON.stringify(userDetails),
      {
        headers,
      }
    );
  }

  updateLeave(
    leaveId: string,
    status: string,
    comment: string
  ): Observable<any> {
    return this.http.put(
      `${mainUrl}/api/manager/update-leave/${leaveId}`,
      { status, comment },
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          Authorization: `${
            this.authService.getUserRole() === 'MANAGER' ? 'managerToken' : ''
          }`,
        }),
      }
    );
  }

  getAllLeaveApplications(): Observable<any> {
    return this.http.get(`${mainUrl}/api/manager/get-all-leaves`, {
      headers: this.headers,
    });
  }

  getPendingLeaveApplications(): Observable<any> {
    return this.http.get(`${mainUrl}/api/manager/get-pending-leaves`, {
      headers: this.headers,
    });
  }

  getActiveLeaveApplications(): Observable<any> {
    return this.http.get(`${mainUrl}/api/manager/get-active-leaves`, {
      headers: this.headers,
    });
  }
}
