import { Injectable, inject } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

/**
 * AuthInterceptor - Inyecta el JWT token en todas las peticiones HTTP
 * Agrega el header Authorization: Bearer <token>
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private readonly authService = inject(AuthService);

  intercept(
    req: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const token = this.authService.getToken();

    if (token) {
      // Clonar la petición y agregar el header Authorization
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(req);
  }
}
