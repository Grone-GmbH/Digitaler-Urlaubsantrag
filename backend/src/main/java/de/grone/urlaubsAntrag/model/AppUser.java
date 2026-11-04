package de.grone.urlaubsAntrag.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Benutzer der Anwendung, heißt "AppUser" (Tabelle (app_user)), weil "user" in SQL reserviert ist
 */
@Entity
@Table(name = "app_user") // Legt den Namen der Tabelle fest
@Getter
@Setter
@NoArgsConstructor // Erstellt ein Konstruktor ohne Parameter
public class AppUser {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatisch id
    private Long id; // Long kann NULL sein, solange das Objekt noch nicht gespeichert ist

    @Column(nullable = false, unique = true) // NULLABLE kann nicht null sein, UNIQUE nicht gleiche Name
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private int annualLeaveDays = 30; // Urlaubsanspruch

    @Column(nullable = false)
    private String fullName;

    public AppUser(String username, String passwordHash, String fullName, Role role, int annualLeaveDays) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.annualLeaveDays = annualLeaveDays;
    }
    /*
     * @NoArgsConstruktor nicht löschen
     * Hibernate braucht einen leeren Konstruktor
     */
}
