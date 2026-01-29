/**
 * AuthResponse Interface
 * Response from authentication endpoints
 * Contains JWT token, refresh token, and user data
 */
import { User } from './user';

export interface AuthResponse {
  token: string; // JWT Access Token
  refreshToken: string; // JWT Refresh Token
  user: User; // Authenticated user data
}
