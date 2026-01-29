import { Component, inject, OnInit, ChangeDetectionStrategy, signal } from '@angular/core';
import { StatsService, DashboardStats } from '../../core/services/stats.service';
import { StatsWidgetComponent } from '../../shared/components/stats-widget/stats-widget.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, StatsWidgetComponent],
  templateUrl: './dashboard.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DashboardComponent implements OnInit {
  private readonly statsService = inject(StatsService);

  stats = signal<DashboardStats | null>(null);

  ngOnInit(): void {
    this.loadStats();
  }

  private loadStats(): void {
    this.statsService.getDashboardStats().subscribe({
      next: (stats) => this.stats.set(stats),
      error: (err) => console.error('Error loading stats:', err),
    });
  }
}
