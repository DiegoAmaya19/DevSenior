# Checklist de Implementación - BiblioKeep Frontend

## ✅ Completado (Fase 1: Marco Visual)

### Estructura de Carpetas
- [x] `app/core/` - Lógica central
- [x] `app/core/models/` - Interfaces TypeScript
- [x] `app/core/services/` - Servicios (estructura lista)
- [x] `app/core/interceptors/` - Interceptores (estructura lista)
- [x] `app/shared/` - Componentes reutilizables
- [x] `app/shared/components/` - Componentes atómicos
- [x] `app/features/` - Módulos de funcionalidad
- [x] `app/features/dashboard/` - Dashboard feature
- [x] `app/features/library/` - Biblioteca feature
- [x] `app/features/loans/` - Préstamos feature

### Interfaces de Datos
- [x] `User` (usuario autenticado)
- [x] `Book` (libro con estado)
- [x] `Loan` (préstamo de libro)
- [x] `AuthResponse` (respuesta de login)
- [x] `BookStatus` (enum de estados)

### Layout Principal
- [x] `MainLayoutComponent` (standalone)
- [x] Sidebar responsivo y colapsable
- [x] Header con nombre de app "BiblioKeep"
- [x] Router outlet para contenido
- [x] Navegación con routerLink
- [x] Drawer móvil (<1024px)
- [x] Tailwind CSS 100% puro
- [x] Transiciones suaves

### Rutas
- [x] Ruta raíz `/` → MainLayout
- [x] Ruta `/dashboard` → DashboardComponent
- [x] Ruta `/library` → LibraryComponent
- [x] Ruta `/loans` → LoansComponent
- [x] Redirección por defecto a `/dashboard`

---

## 🔄 En Progreso (Fase 2: Servicios & Autenticación)

### Core Services
- [ ] `AuthService` - Manejo de login/register/logout
- [ ] `BookService` - CRUD de libros
- [ ] `LoanService` - CRUD de préstamos
- [ ] `StatsService` - Estadísticas del dashboard

### Interceptores
- [ ] `AuthInterceptor` - Inyectar JWT en headers
- [ ] `ErrorInterceptor` - Manejo global de errores

### Componentes Atómicos (Shared)
- [ ] `ButtonComponent` - Botones con variantes (primary, secondary, danger)
- [ ] `BookCardComponent` - Card para mostrar libros
- [ ] `StatsWidgetComponent` - Widget para estadísticas
- [ ] `FormInputComponent` - Input reutilizable
- [ ] `LoaderComponent` - Spinner/skeleton

---

## ⏳ Pendiente (Fase 3: Features & Lógica)

### Dashboard Feature
- [ ] Componente principal
- [ ] Mostrar estadísticas anuales
- [ ] Gráficas de lectura
- [ ] Libros recientes
- [ ] Meta de lectura

### Library Feature
- [ ] Listado de libros
- [ ] Búsqueda y filtrado
- [ ] Agregar libro manual
- [ ] Búsqueda por ISBN (Google Books API)
- [ ] Editar estado de libro
- [ ] Calificación

### Loans Feature
- [ ] Listado de préstamos
- [ ] Crear nuevo préstamo
- [ ] Marcar como devuelto
- [ ] Notificaciones de vencimiento

### Auth (Feature)
- [ ] Login page
- [ ] Registro page
- [ ] Persistencia de token
- [ ] Auth guard para rutas protegidas

---

## 🛠 Herramientas & Dependencias

### Actualmente Instalado
- [x] Angular 21
- [x] Tailwind CSS 4
- [x] TypeScript 5.9
- [x] RxJS 7.8

### Pendiente de Instalar
- [ ] `lucide-angular` - Iconos (opcional, tenemos SVG inline)
- [ ] `@angular-eslint/*` - Linting (opcional)
- [ ] `vitest` - Testing (opcional)

---

## 📱 Responsive Breakpoints (Tailwind)

| Breakpoint | Ancho  | Comportamiento Sidebar |
|-----------|--------|------------------------|
| xs        | <640px | Drawer (hidden)        |
| sm        | 640px  | Drawer (hidden)        |
| md        | 768px  | Drawer (hidden)        |
| lg        | 1024px | Fijo (visible)         |
| xl        | 1280px | Fijo (visible)         |

---

## 🎯 Próximas Acciones Recomendadas

1. **Crear `AuthService`** en `core/services/`
   - Métodos: `login()`, `register()`, `logout()`
   - Usar `HttpClient` para peticiones

2. **Implementar `AuthInterceptor`** en `core/interceptors/`
   - Inyectar token JWT en headers Authorization
   - Manejo de tokens expirados

3. **Crear componentes atómicos** en `shared/components/`
   - `ButtonComponent` con variantes Tailwind
   - `BookCardComponent` con portada e información

4. **Completar features**
   - Empezar por Dashboard (más simple)
   - Luego Library (CRUD)
   - Finalmente Loans

---

## 📝 Notas de Desarrollo

- **No usar NgModules**: Todos los componentes son standalone
- **Signals por defecto**: Para estado local en componentes
- **OnPush Change Detection**: Agregar `changeDetection: ChangeDetectionStrategy.OnPush`
- **Tailwind para todo**: No usar ngClass ni ngStyle
- **Validación**: Usar `Validators` de `@angular/forms`
- **Tipos estrictos**: No usar `any`, preferir `unknown` o tipos específicos

---

**Última actualización**: 2026-01-29
**Estado General**: ✅ 40% - Marco visual completado
