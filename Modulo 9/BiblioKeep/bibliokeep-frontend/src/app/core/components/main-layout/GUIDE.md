/**
 * MainLayoutComponent Guide
 * 
 * This is the root layout component for the BiblioKeep application.
 * It provides the main structure with a responsive sidebar and header.
 * 
 * Features:
 * - Responsive Design: Sidebar collapses to drawer on mobile (<lg)
 * - Navigation: RouterLink-based navigation to feature routes
 * - Tailwind CSS: 100% pure Tailwind, no external UI libraries
 * - Accessibility: ARIA labels and keyboard support
 * - State Management: Uses Signals for sidebar toggle state
 * 
 * Usage:
 * The MainLayoutComponent is already integrated into the root routes
 * as the main wrapper. All feature components render within its router-outlet.
 * 
 * Route Structure:
 * /
 * ├── /dashboard    (Dashboard feature)
 * ├── /library      (Library feature)
 * └── /loans        (Loans feature)
 * 
 * Customize:
 * 1. Navigation items: Edit navItems array in main-layout.component.ts
 * 2. Colors: Use Tailwind classes (currently slate-900, blue-600)
 * 3. Icons: Replace SVG placeholders with lucide-angular when installed
 * 4. Sidebar width: Change w-64 to w-72 or other Tailwind size
 */
