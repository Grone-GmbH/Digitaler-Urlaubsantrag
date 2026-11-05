package de.grone.urlaubsAntrag.repository;

import de.grone.urlaubsAntrag.model.AppUser;
import de.grone.urlaubsAntrag.model.LeaveRequest;
import de.grone.urlaubsAntrag.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByTraineeOrderByCreatedAtDesc(AppUser trainee);

    List<LeaveRequest> findAllByOrderByCreatedAtDesc();

    /** Zählt Anträge des Azubis, die sich mit dem Zeitraum überschneiden. */
    @Query("select count(r) from LeaveRequest r "
            + "where r.trainee = :trainee and r.status in :statuses "
            + "and r.startDate <= :rangeEnd and r.endDate >= :rangeStart")
    long countOverlapping(@Param("trainee") AppUser trainee,
                          @Param("statuses") Collection<LeaveStatus> statuses,
                          @Param("rangeStart") LocalDate rangeStart,
                          @Param("rangeEnd") LocalDate rangeEnd);

    /** Summe der Urlaubstage eines Azubis mit einem Status in einem Zeitraum (nach Startdatum). */
    @Query("select coalesce(sum(r.days), 0L) from LeaveRequest r "
            + "where r.trainee = :trainee and r.status = :status "
            + "and r.startDate >= :rangeStart and r.startDate <= :rangeEnd")
    Long sumDays(@Param("trainee") AppUser trainee,
                 @Param("status") LeaveStatus status,
                 @Param("rangeStart") LocalDate rangeStart,
                 @Param("rangeEnd") LocalDate rangeEnd);
}
