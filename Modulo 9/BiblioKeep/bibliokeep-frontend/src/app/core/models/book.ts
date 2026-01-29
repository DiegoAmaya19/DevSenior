/**
 * Book Interface
 * Matches Backend: BookResponseDTO
 * Represents a book in the user's collection
 */
export type BookStatus = 'DESEADO' | 'COMPRADO' | 'LEYENDO' | 'LEIDO' | 'ABANDONADO';

export interface Book {
  id: number; // Long - Auto-incremented from backend
  ownerId: string; // UUID - Relation to User
  isbn: string; // 10 or 13 digits
  title: string;
  authors: string[]; // List<String>
  description: string;
  thumbnail: string; // Image URL
  status: BookStatus; // DESEADO, COMPRADO, LEYENDO, LEIDO, ABANDONADO
  rating: number | null; // 1-5, nullable
  isLent: boolean; // Default: false
}
