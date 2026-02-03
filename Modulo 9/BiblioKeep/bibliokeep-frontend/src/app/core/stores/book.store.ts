import { Injectable, inject, signal, computed, effect } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book, BookStatus } from '../models';

/**
 * BookStoreService - Store centralizado para libros basado en Signals
 * Maneja estado local, búsqueda y operaciones optimistas
 */
@Injectable({
  providedIn: 'root',
})
export class BookStoreService {
  private readonly bookService = inject(BookService);

  // State signals
  private readonly booksState = signal<Book[]>([]);
  private readonly isLoadingState = signal(false);
  private readonly selectedBookState = signal<Book | null>(null);
  private readonly searchQueryState = signal('');
  private readonly statusFilterState = signal<BookStatus | 'ALL'>('ALL');

  // Public signals (read-only)
  readonly books = this.booksState.asReadonly();
  readonly isLoading = this.isLoadingState.asReadonly();
  readonly selectedBook = this.selectedBookState.asReadonly();
  readonly searchQuery = this.searchQueryState.asReadonly();
  readonly statusFilter = this.statusFilterState.asReadonly();

  // Computed signals
  readonly filteredBooks = computed(() => {
    const books = this.books();
    const query = this.searchQuery().toLowerCase();
    const filter = this.statusFilter();

    return books.filter((book) => {
      // Filter by status
      if (filter !== 'ALL' && book.status !== filter) {
        return false;
      }

      // Filter by search query (title, authors)
      if (query) {
        const matchTitle = book.title.toLowerCase().includes(query);
        const matchAuthors = book.authors.some((author) =>
          author.toLowerCase().includes(query),
        );
        return matchTitle || matchAuthors;
      }

      return true;
    });
  });

  readonly totalBooks = computed(() => this.books().length);

  readonly booksRead = computed(
    () => this.books().filter((b) => b.status === 'LEIDO').length,
  );

  readonly currentlyReading = computed(
    () => this.books().filter((b) => b.status === 'LEYENDO').length,
  );

  constructor() {
    this.loadBooks();
  }

  /**
   * Cargar detalle de un libro por ID
   */
  loadBookDetail(id: number): void {
    this.isLoadingState.set(true);
    this.bookService.getBook(id).subscribe({
      next: (book) => {
        this.selectedBookState.set(book);
        this.isLoadingState.set(false);
      },
      error: (err) => {
        console.error('Error loading book:', err);
        this.isLoadingState.set(false);
      },
    });
  }

  /**
   * Limpiar selección de libro
   */
  clearSelectedBook(): void {
    this.selectedBookState.set(null);
  }

  /**
   * Cargar todos los libros
   */
  private loadBooks(): void {
    this.isLoadingState.set(true);

    this.bookService.getBooks().subscribe({
      next: (books) => {
        this.booksState.set(books);
        this.isLoadingState.set(false);
      },
      error: (err) => {
        console.error('Error loading books:', err);
        this.isLoadingState.set(false);
      },
    });
  }

  /**
   * Actualizar búsqueda
   */
  setSearchQuery(query: string): void {
    this.searchQueryState.set(query);
  }

  /**
   * Actualizar filtro por estado
   */
  setStatusFilter(status: BookStatus | 'ALL'): void {
    this.statusFilterState.set(status);
  }

  /**
   * Actualizar rating con Optimistic UI
   * Actualiza localmente primero, luego hace la petición HTTP
   */
  updateBookRating(bookId: number, rating: number): void {
    // Optimistic UI: Actualizar localmente
    const books = this.booksState();
    const bookIndex = books.findIndex((b) => b.id === bookId);

    if (bookIndex === -1) return;

    const oldBook = books[bookIndex];
    const updatedBooks = [...books];
    updatedBooks[bookIndex] = { ...oldBook, rating };
    this.booksState.set(updatedBooks);

    // Petición HTTP (sin bloquear UI)
    this.bookService.updateBook(bookId, { rating }).subscribe({
      error: (err) => {
        console.error('Error updating rating:', err);
        // Revertir cambio si falla
        const currentBooks = this.booksState();
        const rollbackIndex = currentBooks.findIndex((b) => b.id === bookId);
        if (rollbackIndex !== -1) {
          const rolled = [...currentBooks];
          rolled[rollbackIndex] = oldBook;
          this.booksState.set(rolled);
        }
      },
    });
  }

  /**
   * Actualizar status con Optimistic UI
   */
  updateBookStatus(bookId: number, status: BookStatus): void {
    // Optimistic UI
    const books = this.booksState();
    const bookIndex = books.findIndex((b) => b.id === bookId);

    if (bookIndex === -1) return;

    const oldBook = books[bookIndex];
    const updatedBooks = [...books];
    updatedBooks[bookIndex] = { ...oldBook, status };
    this.booksState.set(updatedBooks);

    // Petición HTTP
    this.bookService.updateBookStatus(bookId, status).subscribe({
      error: (err) => {
        console.error('Error updating status:', err);
        // Revertir cambio si falla
        const currentBooks = this.booksState();
        const rollbackIndex = currentBooks.findIndex((b) => b.id === bookId);
        if (rollbackIndex !== -1) {
          const rolled = [...currentBooks];
          rolled[rollbackIndex] = oldBook;
          this.booksState.set(rolled);
        }
      },
    });
  }

  /**
   * Agregar libro a la colección
   */
  addBook(book: Omit<Book, 'id' | 'ownerId'>): void {
    this.isLoadingState.set(true);

    this.bookService.createBook(book).subscribe({
      next: (newBook) => {
        const books = this.booksState();
        this.booksState.set([...books, newBook]);
        this.isLoadingState.set(false);
      },
      error: (err) => {
        console.error('Error adding book:', err);
        this.isLoadingState.set(false);
      },
    });
  }

  /**
   * Eliminar libro de la colección
   */
  deleteBook(bookId: number): void {
    this.isLoadingState.set(true);

    this.bookService.deleteBook(bookId).subscribe({
      next: () => {
        const books = this.booksState();
        this.booksState.set(books.filter((b) => b.id !== bookId));
        this.isLoadingState.set(false);
      },
      error: (err) => {
        console.error('Error deleting book:', err);
        this.isLoadingState.set(false);
      },
    });
  }

  /**
   * Recargar libros
   */
  reload(): void {
    this.setSearchQuery('');
    this.setStatusFilter('ALL');
    this.loadBooks();
  }
}
