import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.isAuthenticated()) {
    router.navigate(['/login']);
    return false;
  }

  const requiredRoles = route.data['role'];
  const userRole = authService.getUserRole();

  if (requiredRoles && requiredRoles !== userRole) {
    router.navigate(['/login']);
    return false;
  }

  console.log('adf');

  return true;
};
