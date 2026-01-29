import { Component, inject, OnInit, ChangeDetectionStrategy, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanService } from '../../core/services/loan.service';
import { Loan } from '../../core/models';
import { ButtonComponent } from '../../shared/components/button/button.component';

@Component({
  selector: 'app-loans',
  standalone: true,
  imports: [CommonModule, ButtonComponent],
  templateUrl: './loans.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoansComponent implements OnInit {
  private readonly loanService = inject(LoanService);

  loans = signal<Loan[]>([]);

  ngOnInit(): void {
    this.loadLoans();
  }

  private loadLoans(): void {
    this.loanService.getLoans().subscribe({
      next: (loans) => this.loans.set(loans),
      error: (err) => console.error('Error loading loans:', err),
    });
  }

  onCreateLoan(): void {
    // TODO: Implementar modal para crear préstamo
    console.log('Crear préstamo');
  }

  onMarkReturned(loanId: number): void {
    this.loanService.markAsReturned(loanId).subscribe({
      next: () => this.loadLoans(),
      error: (err) => console.error('Error marking as returned:', err),
    });
  }
}
