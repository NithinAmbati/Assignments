import { Component, OnInit } from '@angular/core';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-add-employee',
  imports: [],
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.css',
})
export class AddEmployeeComponent implements OnInit {
  username = '';
  password = '';
  email = '';
  showPassword = false;

  constructor(private managerService: ManagerService) {}

  onChangeUsername = (event: Event): void => {
    this.username = (event.target as HTMLInputElement).value;
  };

  onChangePassword = (event: Event): void => {
    this.password = (event.target as HTMLInputElement).value;
  };

  onChangeEmail = (event: Event): void => {
    this.email = (event.target as HTMLInputElement).value;
  };

  toggleShowPassword = (): void => {
    this.showPassword = !this.showPassword;
  };

  onSubmit = (event: Event): void => {
    event.preventDefault();
    console.log(this.username, this.password, this.email);
    this.managerService
      .addEmployee({
        username: this.username,
        password: this.password,
        email: this.email,
      })
      .subscribe({
        next: (res) => console.log(res),
        error: (error) => console.log(error),
      });
  };

  ngOnInit(): void {}
}
