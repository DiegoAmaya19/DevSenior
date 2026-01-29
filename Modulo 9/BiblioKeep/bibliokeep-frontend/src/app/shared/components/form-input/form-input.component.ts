import { Component, input, output, ChangeDetectionStrategy } from '@angular/core';
import { FormsModule } from '@angular/forms';

/**
 * FormInputComponent - Input reutilizable
 */
@Component({
  selector: 'app-form-input',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form-input.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FormInputComponent {
  label = input<string>('');
  type = input<string>('text');
  placeholder = input<string>('');
  isDisabled = input<boolean>(false);
  error = input<string>('');

  value = '';
  onValueChange = output<string>();
}
