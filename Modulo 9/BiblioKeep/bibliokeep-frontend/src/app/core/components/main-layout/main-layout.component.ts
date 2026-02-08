import { Component, signal, ChangeDetectionStrategy, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

/**
 * MainLayoutComponent - Standalone Layout Component
 * Provides the main application structure with:
 * - Responsive sidebar (collapsible drawer on mobile)
 * - Header with app title
 * - Main content area with router-outlet
 */
@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class MainLayoutComponent {
  private readonly authService = inject(AuthService);

  // Sidebar open state signal
  sidebarOpen = signal(false);

  // Current user
  user = signal(this.authService.getCurrentUser());

  // Navigation items with emojis
  navItems = [
    { label: 'Dashboard', route: '/dashboard', emoji: '📊' },
    { label: 'Mi Biblioteca', route: '/library', emoji: '📚' },
    { label: 'Préstamos', route: '/loans', emoji: '🤝' },
  ];

  /**
   * Toggle sidebar open/close state
   */
  toggleSidebar(): void {
    this.sidebarOpen.update((value) => !value);
  }

  /**
   * Close sidebar (useful for mobile after navigation)
   */
  closeSidebar(): void {
    this.sidebarOpen.set(false);
  }

  /**
   * Logout
   */
  logout(): void {
    this.authService.logout();
    // Router redirige a login (se configura en guards)
  }
}
