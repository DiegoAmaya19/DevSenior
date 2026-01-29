import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from '../models';

/**
 * LoanService - Gestión de préstamos
 * CRUD de préstamos y notificaciones
 */
@Injectable({
  providedIn: 'root',
})
export class LoanService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = '/api/loans';

  /**
   * Obtener todos los préstamos del usuario
   */
  getLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(this.apiUrl);
  }

  /**
   * Obtener un préstamo por ID
   */
  getLoan(id: number): Observable<Loan> {
    return this.http.get<Loan>(`${this.apiUrl}/${id}`);
  }

  /**
   * Crear nuevo préstamo
   */
  createLoan(loan: Omit<Loan, 'id'>): Observable<Loan> {
    return this.http.post<Loan>(this.apiUrl, loan);
  }

  /**
   * Actualizar préstamo
   */
  updateLoan(id: number, loan: Partial<Loan>): Observable<Loan> {
    return this.http.put<Loan>(`${this.apiUrl}/${id}`, loan);
  }

  /**
   * Marcar como devuelto
   */
  markAsReturned(id: number): Observable<Loan> {
    return this.http.patch<Loan>(`${this.apiUrl}/${id}/return`, {});
  }

  /**
   * Eliminar préstamo
   */
  deleteLoan(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * Obtener préstamos vencidos
   */
  getOverdueLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.apiUrl}/overdue`);
  }
}
