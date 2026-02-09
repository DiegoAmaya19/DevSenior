import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models';

/**
 * BookService - Gestión de libros
 * Endpoints:
 * - GET /api/books
 * - GET /api/books/{id}
 * - POST /api/books
 * - PUT /api/books/{id}
 * - PATCH /api/books/{id}/status
 * - DELETE /api/books/{id}
 */
@Injectable({
  providedIn: 'root',
})
export class BookService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/books';

  /**
   * GET /api/books - Obtener todos los libros del usuario
   */
  getBooks(): Observable<Book[]> {

    const headers = new HttpHeaders({
      'X-User-Id': 'f53b6038-52dd-45de-a123-1ebcd597efd5'
    });

    return this.http.get<Book[]>(this.apiUrl,{headers});
  }

  /**
   * GET /api/books/{id} - Obtener un libro por ID
   */
  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  /**
   * POST /api/books - Crear nuevo libro
   */
  createBook(book: Omit<Book, 'id' | 'ownerId'>): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, book);
  }

  /**
   * PUT /api/books/{id} - Actualizar libro existente (todos los campos)
   */
  updateBook(id: number, book: Partial<Omit<Book, 'id' | 'ownerId'>>): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, book);
  }

  /**
   * DELETE /api/books/{id} - Eliminar libro
   */
  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * PATCH /api/books/{id}/status - Actualizar estado del libro
   * Espera: { status: 'DESEADO' | 'COMPRADO' | 'LEYENDO' | 'LEIDO' | 'ABANDONADO' }
   */
  updateBookStatus(id: number, status: string): Observable<Book> {
    return this.http.patch<Book>(`${this.apiUrl}/${id}/status`, { status });
  }
}
