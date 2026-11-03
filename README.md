## Digitalisierung des Urlaubsantrags für Auszubildende bei Grone
Webanwendung, mit der Auszubildende ihren Urlaub digital beantragen und Ausbilder die Anträge prüfen können.

## Funktionen

### Auszubildende
- Anmelden und eigene Daten ansehen
- Gewünschten Urlaubszeitraum auswählen
- Anzahl der Urlaubstage sehen, Antrag absenden und Status verfolgen
- Bisherige Urlaubsanträge sehen
- Noch nicht bearbeiteten Antrag zurückziehen

### Ausbilder
- Urlaubsanträge anzeigen
- Prüfen, genehmigen, ablehnen und Begründung hinterlegen
- Urlaubshistorie einsehen

### Administrator
- Benutzer zuordnen
- Rollen verwalten
- Alle Urlaubsanträge einsehen

## BUILD INFO
• Frontend: TypeScript, React
• Backend: Java Springboot - REST API
• Datenbank: SQL

## Geplante API-Endpunkte

| Methode | Endpunkt | Beschreibung |
|---|---|---|
| POST | `/api/auth/login` | Anmeldung |
| GET | `/api/trainees` | Auszubildende anzeigen |
| GET | `/api/leave/requests` | Urlaubsanträge anzeigen |
| POST | `/api/leave/requests` | Urlaubsantrag stellen |
| GET | `/api/leave/requests/{id}` | Einzelnen Antrag anzeigen |
| PUT | `/api/leave/requests/{id}/approve` | Antrag genehmigen |
| PUT | `/api/leave/requests/{id}/reject` | Antrag ablehnen |
| GET | `/api/trainees/{id}/leave-balance` | Resturlaub anzeigen |

## Status

In Entwicklung.