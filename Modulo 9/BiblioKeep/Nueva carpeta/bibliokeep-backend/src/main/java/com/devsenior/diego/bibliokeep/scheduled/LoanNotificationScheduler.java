package com.devsenior.diego.bibliokeep.scheduled;

import com.devsenior.diego.bibliokeep.model.entity.Loan;
import com.devsenior.diego.bibliokeep.repository.BookRepository;
import com.devsenior.diego.bibliokeep.repository.LoanRepository;
import com.devsenior.diego.bibliokeep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoanNotificationScheduler {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 8 * * *")
    public void checkOverdueLoans() {
        log.info("Iniciando verificación de préstamos vencidos...");
        
        var today = LocalDate.now();
        var overdueLoans = loanRepository.findOverdueLoans(today);
        
        if (overdueLoans.isEmpty()) {
            log.info("No se encontraron préstamos vencidos.");
            return;
        }

        log.info("Se encontraron {} préstamo(s) vencido(s).", overdueLoans.size());

        for (Loan loan : overdueLoans) {
            try {
                var book = bookRepository.findById(loan.getBookId());
                
                if (book.isEmpty()) {
                    log.warn("Libro con ID {} no encontrado para el préstamo {}", loan.getBookId(), loan.getId());
                    continue;
                }

                var ownerId = book.get().getOwnerId();
                var user = userRepository.findById(ownerId);
                
                if (user.isEmpty()) {
                    log.warn("Usuario con ID {} no encontrado para el préstamo {}", ownerId, loan.getId());
                    continue;
                }

                var userEmail = user.get().getEmail();
                var contactName = loan.getContactName();
                var bookTitle = book.get().getTitle();
                var daysOverdue = today.toEpochDay() - loan.getDueDate().toEpochDay();

                log.info("""
                    Enviando notificación de mora:
                    - Usuario: {} ({})
                    - Libro: {}
                    - Contacto: {}
                    - Días de retraso: {}
                    - Fecha de vencimiento: {}
                    """, userEmail, ownerId, bookTitle, contactName, daysOverdue, loan.getDueDate());

                // TODO: Implementar envío de correo usando JavaMailSender cuando esté disponible
                // Por ahora solo se registra en el log
                
            } catch (Exception e) {
                log.error("Error al procesar préstamo vencido con ID {}: {}", loan.getId(), e.getMessage(), e);
            }
        }

        log.info("Finalizada verificación de préstamos vencidos.");
    }
}
