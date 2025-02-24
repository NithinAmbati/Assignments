import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { employeeHeaderList, managerHeaderList } from '../../store/data';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { DropdownComponent } from '../../components/dropdown/dropdown.component';

@Component({
  selector: 'app-header',
  imports: [RouterModule, CommonModule, DropdownComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  constructor(private authService: AuthService) {}

  headersList: Array<any> = [];
  ltsDropDown: boolean = false;
  username: string = '';

  ngOnInit(): void {
    const userRole = this.authService.getUserRole();
    if (userRole === 'EMPLOYEE') {
      this.headersList = employeeHeaderList;
    } else if (userRole === 'MANAGER') {
      this.headersList = managerHeaderList;
    }
    this.username = this.authService.getUsername();
  }

  onClickLogoutBtn(): void {
    this.authService.logout();
  }

  showLtsDropDown() {
    this.ltsDropDown = true;
  }

  hideLtsDropDown() {
    this.ltsDropDown = false;
  }
}
