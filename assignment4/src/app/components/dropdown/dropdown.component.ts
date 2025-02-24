import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

type dropdownListItem = {
  title: string;
  link: string;
};

@Component({
  selector: 'app-dropdown',
  imports: [CommonModule, RouterModule],
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.css',
})
export class DropdownComponent {
  @Input() dropdownList: dropdownListItem[] = [];
  @Input() title: string = '';

  dropdown: boolean = false;

  constructor(private authService: AuthService) {}

  showDropdown() {
    this.dropdown = true;
  }

  hideDropdown() {
    this.dropdown = false;
  }

  logout() {
    this.authService.logout();
  }
}
