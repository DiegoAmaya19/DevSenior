import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { User, AuthResponse } from '../models';

/**
 * AuthService - Gestión de autenticación
 * Maneja login, registro y persistencia de tokens JWT
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = '/api/users';

  // Observable para el usuario autenticado
  private userSubject = new BehaviorSubject<User | null>(null);
  public user$ = this.userSubject.asObservable();

  // Observable para el token
  private tokenSubject = new BehaviorSubject<string | null>(null);
  public token$ = this.tokenSubject.asObservable();

  constructor() {
    // Recuperar token y usuario del localStorage al iniciar
    this.loadFromStorage();
  }

  /**
   * Registrar nuevo usuario
   */
  register(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, {
      email,
      password,
    });
  }

  /**
   * Iniciar sesión
   */
  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, {
      email,
      password,
    }).pipe(
      tap((response) => this.setAuthData(response))
    );
  }

  /**
   * Cerrar sesión
   */
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.tokenSubject.next(null);
  }

  /**
   * Obtener token actual
   */
  getToken(): string | null {
    return this.tokenSubject.value;
  }

  /**
   * Obtener usuario actual
   */
  getCurrentUser(): User | null {
    return this.userSubject.value;
  }

  /**
   * Verificar si está autenticado
   */
  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  /**
   * Guardar datos de autenticación
   */
  private setAuthData(response: AuthResponse): void {
    localStorage.setItem('token', response.token);
    localStorage.setItem('user', JSON.stringify(response.user));
    this.userSubject.next(response.user);
    this.tokenSubject.next(response.token);
  }

  /**
   * Cargar datos del localStorage
   */
  private loadFromStorage(): void {
    const token = localStorage.getItem('token');
    const user = localStorage.getItem('user');

    if (token) {
      this.tokenSubject.next(token);
    }

    if (user) {
      try {
        this.userSubject.next(JSON.parse(user));
      } catch (error) {
        console.error('Error parsing user from storage:', error);
      }
    }
  }
}
