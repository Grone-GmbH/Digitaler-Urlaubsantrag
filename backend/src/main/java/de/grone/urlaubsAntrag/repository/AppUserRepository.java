package de.grone.urlaubsAntrag.repository;

import de.grone.urlaubsAntrag.model.AppUser;
import de.grone.urlaubsAntrag.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username); // SELECT ...

    List<AppUser> findByRole(Role role); // WHERE role = ?
}


/*
 * JpaRepository ist interface im Framework Spring Data JPA, die den Datenzugriff auf relationale Datenbanken stark vereinfacht
 * findById() Benutzer anhand der ID suchen
 * findAll() Alle Benutzer abrufen
 * deleteById() Benutzer anhand der ID löschen
 */