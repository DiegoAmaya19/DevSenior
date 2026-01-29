import { Component, input, ChangeDetectionStrategy } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Book } from '../../../core/models';

/**
 * BookCardComponent - Card para mostrar información de libros
 * Muestra portada, título, autores y estado
 */
@Component({
  selector: 'app-book-card',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './book-card.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BookCardComponent {
  book = input.required<Book>();

  getStatusClass(): string {
    const baseClass = 'inline-block px-2 py-1 rounded text-xs font-semibold';
    const statusClass = {
      DESEADO: 'bg-blue-100 text-blue-800',
      COMPRADO: 'bg-green-100 text-green-800',
      LEYENDO: 'bg-yellow-100 text-yellow-800',
      LEIDO: 'bg-purple-100 text-purple-800',
      ABANDONADO: 'bg-gray-100 text-gray-800',
    }[this.book().status];

    return `${baseClass} ${statusClass}`;
  }

  getStatusLabel(): string {
    const labels = {
      DESEADO: 'Deseado',
      COMPRADO: 'Comprado',
      LEYENDO: 'Leyendo',
      LEIDO: 'Leído',
      ABANDONADO: 'Abandonado',
    };
    return labels[this.book().status];
  }
}
