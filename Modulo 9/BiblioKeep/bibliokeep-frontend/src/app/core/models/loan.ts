/**
 * Loan Interface
 * Matches Backend: LoanResponseDTO
 * Represents a book loan to a contact
 */
export interface Loan {
  id: number; // Long
  bookId: number; // Long - Foreign Key
  contactName: string;
  loanDate: string; // LocalDate (ISO 8601 format: YYYY-MM-DD)
  dueDate: string; // LocalDate (ISO 8601 format: YYYY-MM-DD)
  returned: boolean; // Default: false
}
