/**
 * Project Structure Documentation
 * 
 * /src/app
 * ├── core/                           # Core application logic
 * │   ├── models/                     # TypeScript interfaces
 * │   │   ├── user.ts
 * │   │   ├── book.ts
 * │   │   ├── loan.ts
 * │   │   ├── auth-response.ts
 * │   │   └── index.ts                # Central export
 * │   ├── services/                   # Business logic services
 * │   │   ├── auth.service.ts         # Authentication
 * │   │   ├── book.service.ts         # Book management
 * │   │   ├── loan.service.ts         # Loan management
 * │   │   ├── stats.service.ts        # Statistics
 * │   │   └── index.ts
 * │   ├── interceptors/               # HTTP interceptors
 * │   │   ├── auth.interceptor.ts     # JWT token injection
 * │   │   └── index.ts
 * │   └── components/                 # Core layout components
 * │       ├── main-layout/            # Root layout (sidebar, header)
 * │       └── index.ts
 * │
 * ├── shared/                         # Reusable components & utilities
 * │   ├── components/                 # Atomic components (reusable)
 * │   │   ├── button/                 # Button component
 * │   │   ├── book-card/              # Book card component
 * │   │   ├── stats-widget/           # Stats widget
 * │   │   ├── form-input/             # Form input
 * │   │   └── index.ts                # Central export
 * │   └── utils/                      # Shared utilities
 * │
 * ├── features/                       # Feature modules
 * │   ├── dashboard/                  # Dashboard feature
 * │   │   ├── dashboard.component.ts
 * │   │   ├── dashboard.routes.ts
 * │   │   └── pages/
 * │   ├── library/                    # Library management
 * │   │   ├── library.component.ts
 * │   │   ├── library.routes.ts
 * │   │   └── pages/
 * │   └── loans/                      # Loan management
 * │       ├── loans.component.ts
 * │       ├── loans.routes.ts
 * │       └── pages/
 * │
 * ├── app.ts                          # Root component
 * ├── app.routes.ts                   # Root routes
 * ├── app.config.ts                   # Application config
 * ├── app.html                        # Root template
 * └── app.css                         # Global styles
 */
