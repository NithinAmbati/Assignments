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

  rejectModal: boolean = false;
  selectedLeave: any = null;

  comment: string = '';

  ngOnInit(): void {
    this.managerService.getPendingLeaveApplications().subscribe((data) => {
      return (this.leaveApplications = data.data);
    });
  }

  acceptLeave(leaveId: string): void {
    this.managerService.updateLeave(leaveId, 'ACCEPT', this.comment).subscribe({
      next: (res) => alert(res.message),
      error: (error) => alert(error.error.message),
    });
  }

  rejectLeave(event: Event, leaveId: string): void {
    event?.preventDefault();
    this.managerService.updateLeave(leaveId, 'REJECT', this.comment).subscribe({
      next: (res) => alert(res.message),
      error: (error) => alert(error.error.message),
    });
    this.rejectModal = false;
  }

  onChangeComment(event: Event) {
    this.comment = (event.target as HTMLInputElement).value;
  }

  openRejectModal(leaveApplication: any) {
    this.selectedLeave = leaveApplication;
    console.log(this.selectedLeave);
    this.rejectModal = true;
  }

  closeRejectModal() {
    this.rejectModal = false;
  }
}
