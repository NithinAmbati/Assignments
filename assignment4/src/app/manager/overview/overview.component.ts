import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { ManagerService } from '../../services/manager.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-overview',
  imports: [CommonModule],
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css',
})
export class OverviewComponent implements OnInit {
  leaveApplications: any[] = [];
  selectedLeavesType: string = 'ALL';
  constructor(private managerService: ManagerService) {}
  ngOnInit(): void {
    if (this.selectedLeavesType === 'ACTIVE') this.fetchActiveLeavesData();
    else this.fetchAllLeavesData();
  }

  fetchAllLeavesData(): void {
    this.managerService.getAllLeaveApplications().subscribe({
      next: (res) => (
        (this.leaveApplications = res.data), console.log(res.data)
      ),
      error: (error) => console.log(error.error.message),
    });
  }

  fetchActiveLeavesData(): void {
    this.managerService.getActiveLeaveApplications().subscribe({
      next: (res) => (
        (this.leaveApplications = res.data), console.log(res.data)
      ),
      error: (error) => console.log(error.error.message),
    });
  }

  onChangeSelectBar(event: Event): void {
    this.selectedLeavesType = (event.target as HTMLInputElement).value;
    if (this.selectedLeavesType === 'ACTIVE') this.fetchActiveLeavesData();
    else this.fetchAllLeavesData();
  }
}
