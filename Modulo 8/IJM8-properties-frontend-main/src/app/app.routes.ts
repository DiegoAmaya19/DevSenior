import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/properties',
        pathMatch: 'full'
    },
    {
        path: 'properties',
        loadChildren: () => import('./features/properties/routes').then(m => m.propertyRoutes)
    },
    {
        path: '**',
        loadComponent: () => import('./features/shared/components/not-found/not-found').then(m => m.NotFound)
    }
];
