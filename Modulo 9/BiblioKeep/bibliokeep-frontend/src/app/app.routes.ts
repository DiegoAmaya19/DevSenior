import { Routes } from '@angular/router';
import { MainLayoutComponent } from './core/components';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { LibraryComponent } from './features/library/library.component';
import { LoansComponent } from './features/loans/loans.component';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'library',
        component: LibraryComponent,
      },
      {
        path: 'loans',
        component: LoansComponent,
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },
];