import { NgClass } from '@angular/common';
import { Component, input, ChangeDetectionStrategy } from '@angular/core';
import { LucideAngularModule } from 'lucide-angular';

export interface StatsData {
  label: string;
  value: number | string;
  icon?: string;
  color?: 'blue' | 'green' | 'purple' | 'yellow';
}

/**
 * StatsWidgetComponent - Widget para mostrar estadísticas
 */
@Component({
  selector: 'app-stats-widget',
  standalone: true,
  imports: [LucideAngularModule, NgClass],
  templateUrl: './stats-widget.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StatsWidgetComponent {
  stat = input.required<StatsData>();

  getWidgetClass(): string {
    const baseClass = 'rounded-lg p-6 shadow-sm';
    const colorClass = {
      blue: 'bg-blue-50 text-blue-900',
      green: 'bg-green-50 text-green-900',
      purple: 'bg-purple-50 text-purple-900',
      yellow: 'bg-yellow-50 text-yellow-900',
    }[this.stat().color || 'blue'];

    return `${baseClass} ${colorClass}`;
  }
}
