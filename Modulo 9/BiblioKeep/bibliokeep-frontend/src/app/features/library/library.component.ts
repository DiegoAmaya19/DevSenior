import { Component, inject, ChangeDetectionStrategy, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookStoreService } from '../../core/stores/book.store';
import { BookStatus } from '../../core/models';
import { BookCardComponent } from '../../shared/components/book-card/book-card.component';
import { ButtonComponent } from '../../shared/components/button/button.component';

@Component({
  selector: 'app-library',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    BookCardComponent,
    ButtonComponent,
  ],
  templateUrl: './library.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LibraryComponent {
  private readonly bookStore = inject(BookStoreService);

  // Expose store signals to template
  readonly filteredBooks = this.bookStore.filteredBooks;
  readonly isLoading = this.bookStore.isLoading;
  readonly totalBooks = this.bookStore.totalBooks;
  readonly booksRead = this.bookStore.booksRead;
  readonly currentlyReading = this.bookStore.currentlyReading;
  readonly searchQuery = this.bookStore.searchQuery;
  readonly statusFilter = this.bookStore.statusFilter;

  // Local state for search input
  readonly searchInput = signal('');

  // Selected book detail
  readonly selectedBook = this.bookStore.selectedBook;

  readonly statuses: BookStatus[] = [
    'DESEADO',
    'COMPRADO',
    'LEYENDO',
    'LEIDO',
    'ABANDONADO',
  ];

  /**
   * Manejar cambio en la búsqueda
   */
  onSearchChange(query: string): void {
    this.searchInput.set(query);
    // Búsqueda local (sin HTTP): solo si tiene contenido relevante
    if (query.length > 0) {
      this.bookStore.setSearchQuery(query);
    } else {
      this.bookStore.setSearchQuery('');
    }
  }

  /**
   * Limpiar búsqueda
   */
  clearSearch(): void {
    this.searchInput.set('');
    this.bookStore.setSearchQuery('');
  }

  /**
   * Cambiar filtro de estado
   */
  onStatusFilterChange(status: BookStatus | 'ALL'): void {
    this.bookStore.setStatusFilter(status);
  }

  /**
   * Actualizar rating del libro (Optimistic UI)
   */
  onRatingChange(bookId: number, rating: number): void {
    this.bookStore.updateBookRating(bookId, rating);
  }

  /**
   * Actualizar estado del libro (Optimistic UI)
   */
  onStatusChange(bookId: number, event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.bookStore.updateBookStatus(bookId, target.value as BookStatus);
  }

  /**
   * Eliminar libro con confirmación
   */
  onDeleteBook(bookId: number): void {
    const confirmDelete = confirm('¿Estás seguro de que quieres eliminar este libro?');
    if (confirmDelete) {
      this.bookStore.deleteBook(bookId);
    }
  }

  /**
   * Agregar nuevo libro
   */
  onAddBook(): void {
    // TODO: Implementar modal para agregar libro
    console.log('Abrir modal para agregar libro');
  }

  /**
   * Ver detalle de un libro
   */
  onViewBook(bookId: number): void {
    this.bookStore.loadBookDetail(bookId);
  }

  /**
   * Cerrar panel de detalle
   */
  closeDetail(): void {
    this.bookStore.clearSelectedBook();
  }
}
