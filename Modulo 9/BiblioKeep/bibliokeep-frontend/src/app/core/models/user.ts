/**
 * User Interface
 * Matches Backend: UserResponseDTO
 * Represents a user in the system
 */
export interface User {
  id: string; // UUID
  email: string;
  preferences: string[]; // Set<String> - favorite genres
  annualGoal: number; // Integer, default: 12
}
