/**
 * GESTIÓN DE LIBROS - IMPLEMENTACIÓN COMPLETADA
 * 
 * Arquitectura:
 * ============
 * 
 * 1. SERVICIOS (core/services/)
 *    - BookService: HTTP client para consumir /api/books/search y CRUD
 *    
 * 2. STORE (core/stores/)
 *    - BookStoreService: Administración centralizada con Signals
 *      * books: Signal<Book[]> - Lista de libros
 *      * isLoading: Signal<boolean> - Estado de carga
 *      * searchQuery: Signal<string> - Query de búsqueda
 *      * statusFilter: Signal<BookStatus | 'ALL'> - Filtro por estado
 *      * filteredBooks: computed - Filtra por status y búsqueda
 *      * totalBooks, booksRead, currentlyReading: computed - Estadísticas
 *      
 *    Métodos:
 *    - searchBooks(query): Búsqueda con debounce
 *    - setStatusFilter(status): Cambiar filtro
 *    - updateBookRating(bookId, rating): Optimistic UI
 *    - updateBookStatus(bookId, status): Optimistic UI
 *    - addBook(book): Crear libro
 *    - deleteBook(bookId): Eliminar libro
 *    - reload(): Reiniciar estado
 *    
 * 3. COMPONENTES (features/library/)
 *    - LibraryComponent: Feature principal
 *      * Barra de búsqueda con debounce
 *      * Filtros por estado (Todos, DESEADO, COMPRADO, LEYENDO, LEIDO, ABANDONADO)
 *      * Cuadrícula responsive de BookCards
 *      * Control de rating (5 estrellas interactivas)
 *      * Dropdown para cambiar estado
 *      * Estados: Loading, Vacío, Con contenido
 *      * Optimistic UI para cambios locales inmediatos
 *      
 * CARACTERÍSTICAS IMPLEMENTADAS:
 * =============================
 * 
 * ✅ Búsqueda de libros con /api/books/search
 * ✅ Filtrado por estado (DESEADO, COMPRADO, LEYENDO, LEIDO, ABANDONADO)
 * ✅ Computed signals para estadísticas en tiempo real
 * ✅ Optimistic UI:
 *    - Cambio de rating: actualiza UI → petición HTTP → revierte si falla
 *    - Cambio de estado: actualiza UI → petición HTTP → revierte si falla
 * ✅ Cuadrícula responsive (1 col mobile, 2 tablet, 4 desktop)
 * ✅ Indicadores de carga (spinner)
 * ✅ Estados vacíos con mensajes personalizados
 * ✅ Integración con BookCardComponent
 * 
 * FLUJO DE DATOS:
 * ===============
 * 
 * 1. CARGA INICIAL:
 *    ngOnInit → BookStoreService.loadBooks() → HTTP GET /api/books
 *    → books signal actualizado → template renderiza filteredBooks
 *    
 * 2. BÚSQUEDA:
 *    Usuario escribe → onSearchChange() → (debounce si > 2 caracteres)
 *    → setSearchQuery() → searchBooks(query) → HTTP GET /api/books/search?q=
 *    → books signal actualizado → computed filteredBooks se recalcula
 *    
 * 3. FILTRAR POR ESTADO:
 *    Usuario hace click → onStatusFilterChange() → setStatusFilter()
 *    → No hace HTTP (filtra localmente) → computed filteredBooks se recalcula
 *    
 * 4. OPTIMISTIC UI (Rating):
 *    Usuario hace click en estrella → onRatingChange()
 *    → updateBookRating() en store:
 *       a) Immediatamente actualiza local book.rating en signal
 *       b) Petición HTTP en background PUT /api/books/{id} con { rating }
 *       c) Si falla: revierte el cambio local
 *       
 * 5. OPTIMISTIC UI (Status):
 *    Usuario selecciona estado → onStatusChange()
 *    → updateBookStatus() en store:
 *       a) Immediatamente actualiza local book.status en signal
 *       b) Petición HTTP en background PATCH /api/books/{id}/status
 *       c) Si falla: revierte el cambio local
 * 
 * BENEFICIOS DE ESTA ARQUITECTURA:
 * ==================================
 * 
 * - Separación de preocupaciones: Store maneja estado, componente maneja UI
 * - Reactividad automática: Computed signals se recalculan cuando dependencias cambian
 * - Optimistic UI: Experiencia de usuario inmediata sin esperar HTTP
 * - No necesita NgZone: Compatible con zoneless Angular
 * - Type-safe: Signals y TypeScript proporcionan seguridad de tipos
 * - Testing simple: Store puede testearse sin componentes
 * - Reutilizable: Store puede inyectarse en múltiples componentes
 * 
 * PRÓXIMOS PASOS:
 * ===============
 * 
 * 1. Modal para agregar libros (onAddBook)
 * 2. Modal para editar libros
 * 3. Confirmación de eliminación
 * 4. Toast/snackbar para feedback de operaciones
 * 5. Validación de formularios
 * 6. Sincronización con backend en tiempo real (WebSocket)
 */
