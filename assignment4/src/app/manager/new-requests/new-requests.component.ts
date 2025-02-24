import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';
import { CommonModule } from '@angular/common';
import { leaveApplicationsType } from '../../store/types';

@Component({
  selector: 'app-new-requests',
  imports: [CommonModule],
  templateUrl: './new-requests.component.html',
  styleUrl: './new-requests.component.css',
})
export class NewRequestsComponent {
  leaveApplications: leaveApplicationsType[] = [];
  constructor(private managerService: ManagerService) {}

  ngOnInit(): void {
    this.managerService.getPendingLeaveApplications().subscribe((data) => {
      console.log(data.data);
      return (this.leaveApplications = data.data);
    });
  }

  acceptLeave(leaveId: string): void {
    console.log(leaveId);
    this.managerService.updateLeave(leaveId, 'ACCEPT').subscribe({
      next: (res) => console.log(res),
      error: (error) => console.log(error.error.message),
    });
  }

  rejectLeave(leaveId: string): void {
    this.managerService.updateLeave(leaveId, 'REJECT').subscribe({
      next: (res) => console.log(res),
      error: (error) => console.log(error.error.message),
    });
  }
}
