import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { leaveTypesList } from '../../store/data';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-apply-leave',
  imports: [CommonModule],
  templateUrl: './apply-leave.component.html',
  styleUrl: './apply-leave.component.css',
})
export class ApplyLeaveComponent {
  leaveType: string = 'CASUAL_LEAVE';
  reason: string = 'something';
  startDate: Date = new Date();
  endDate: Date = new Date();
  leaveTypesList: Array<any> = leaveTypesList;

  constructor(private employeeService: EmployeeService) {}

  onChangeLeaveType(event: Event): void {
    this.leaveType = (event.target as HTMLInputElement).value;
  }

  onChangeReason(event: Event): void {
    this.reason = (event.target as HTMLInputElement).value;
  }

  onChangeStartDate(event: Event): void {
    this.startDate = new Date((event.target as HTMLInputElement).value);
  }

  onChangeEndDate(event: Event): void {
    this.endDate = new Date((event.target as HTMLInputElement).value);
  }

  onSubmitLeaveApplication(event: Event): void {
    event.preventDefault();
    this.employeeService
      .applyLeave({
        reason: this.reason,
        leaveType: this.leaveType,
        startDate: this.startDate,
        endDate: this.endDate,
      })
      .subscribe({
        next: (res) => alert(res.message),
        error: (error) => alert(error.message.message),
      });
  }
}
