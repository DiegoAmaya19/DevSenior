import { Component, input, output, ChangeDetectionStrategy } from '@angular/core';

/**
 * ButtonComponent - Botón reutilizable con variantes
 * Variantes: primary (azul), secondary (gris), danger (rojo)
 */
@Component({
  selector: 'app-button',
  standalone: true,
  templateUrl: './button.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ButtonComponent {
  //inputs
  label = input<string>('Click me');
  variant = input<'primary' | 'secondary' | 'danger'>('primary');
  type = input<'button' | 'submit' | 'reset'>('button');
  isDisabled = input<boolean>(false);

  //outputs
  onClick = output<void>();

  getButtonClass(): string {
    const baseClass =
      'px-4 py-2 rounded-lg font-medium transition-colors disabled:opacity-50 disabled:cursor-not-allowed';

    const variantClass = {
      primary: 'bg-blue-600 text-white hover:bg-blue-700',
      secondary: 'bg-gray-200 text-gray-900 hover:bg-gray-300',
      danger: 'bg-red-600 text-white hover:bg-red-700',
    }[this.variant()];

    return `${baseClass} ${variantClass}`;
  }
}