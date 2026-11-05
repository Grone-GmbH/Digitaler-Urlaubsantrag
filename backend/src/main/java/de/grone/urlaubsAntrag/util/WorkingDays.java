package de.grone.urlaubsAntrag.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class WorkingDays {

    private WorkingDays() {
    }

    /**
     * Zählt die Arbeitstage (Montag bis Freitag) zwischen start und end, beide Tage eingeschlossen.
     * Feiertage werden (noch) nicht berücksichtigt - hier könnte später eine externe Feiertags-API eingebunden werden.
     */
    public static int count(LocalDate start, LocalDate end) {
        int days = 0;
        /*
         * d = start == Tag:1
         * d = end == Tag:5
         * (d.plusDays) wenn Samstag oder Sonntag days++
         */
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            DayOfWeek dow = d.getDayOfWeek();
            if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) {
                days++;
            }
        }
        return days;
    }
}
