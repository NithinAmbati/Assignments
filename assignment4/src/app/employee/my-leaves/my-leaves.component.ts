import { Component, OnInit } from '@angular/core';
import { leaveApplicationsType } from '../../store/types';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../services/employee.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './my-leaves.component.html',
  styleUrl: './my-leaves.component.css',
})
export class MyLeavesComponent implements OnInit {
  myLeavesList: leaveApplicationsType[] = [];
  leavesRemaining: number = 0;

  employeeId: string = '';

  constructor(
    private employeeService: EmployeeService,
    private authService: AuthService
  ) {
    this.employeeId = this.authService.getUserId();
  }

  ngOnInit(): void {
    this.employeeService.getLeavesByEmployee(this.employeeId).subscribe({
      next: (res) => (
        (this.myLeavesList = res.data),
        (this.leavesRemaining = res.leavesRemaining)
      ),
      error: (error) => console.log(error.error.message),
    });
  }
}
