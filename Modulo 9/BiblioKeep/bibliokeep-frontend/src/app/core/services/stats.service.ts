import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * StatsService - Estadísticas del dashboard
 */
export interface DashboardStats {
  totalBooks: number;
  booksRead: number;
  currentlyReading: number;
  activeLoans: number;
  annualGoal: number;
  annualProgress: number;
  averageRating: number;
}

@Injectable({
  providedIn: 'root',
})
export class StatsService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = '/api/stats';

  /**
   * Obtener estadísticas del dashboard
   */
  getDashboardStats(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${this.apiUrl}/dashboard`);
  }
}