package de.grone.urlaubsAntrag.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * Annotationen haben gleiche Funktion wie bei AppUser
 */
@Entity
@Table(name = "leave_request")
@Getter
@Setter
@NoArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Viele requests gehören zu einem Benutzer
     */
    @ManyToOne(optional = false) // Spalte darf nicht leer sein
    @JoinColumn(name = "trainee_id") // Foreign key
    private AppUser trainee;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    /**
     * Anzahl der Arbeitstage (Mo-Fr) im Zeitraum
     */
    @Column(nullable = false)
    private int days;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING; // Zeigt status // Neuer Antrag ist PENDING

    /**
     * Begründung des Ausbilders (bei Ablehnung Pflicht)
     */
    @Column(length = 500)
    private String decisionReason;

    @ManyToOne
    @JoinColumn(name = "decided_by_id") // Hier nochmal die Verbindung zu AppUser // Ausbilder, der entschieden hat (kann leer sein)
    private AppUser decidedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // createdAt ist Zeitpunkt, an dem der Req erstellt wird

    private LocalDateTime decidedAt;
}