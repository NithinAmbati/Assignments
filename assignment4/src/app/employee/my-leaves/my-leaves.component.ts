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
  selectedLeave: any = null;
  leaveId: any = null;
  deleteModal: boolean = false;

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

    console.log(this.myLeavesList);
  }

  openEditModal(leave: any) {
    this.selectedLeave = { ...leave };
  }

  closeEditModal(event?: Event) {
    if (
      event &&
      event.target &&
      (event.target as HTMLElement).classList.contains('modal-overlay')
    ) {
      this.selectedLeave = null;
    } else {
      this.selectedLeave = null;
    }
  }

  onChangeSeletedLeaveData(event: Event, key: string) {
    this.selectedLeave[key] = (event.target as HTMLInputElement).value;
  }

  saveChanges() {
    console.log('Updated Leave:', this.selectedLeave);
    this.employeeService.editLeave(this.selectedLeave).subscribe({
      next: (res) => console.log(res),
      error: (error) => console.log(error),
    });
    this.selectedLeave = null;
  }

  openDeleteModal(leaveId: any) {
    console.log(leaveId);
    this.leaveId = leaveId;
    this.deleteModal = true;
  }

  closeDeleteModal() {
    this.deleteModal = false;
  }

  deleteLeave() {
    console.log('deleted');
    this.employeeService.deleteLeave(this.leaveId).subscribe({
      next: (res) => alert(res.message),
      error: (error) => alert(error.error.message),
    });
    this.closeDeleteModal();
  }
}
