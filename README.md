# Like Hero To Zero – CO₂ Emissionen Weltweit

Prototyp einer Java-EE-Webanwendung zur Erfassung und Verwaltung von CO₂-Emissionsdaten. Entwickelt im Rahmen der Fallstudie IPWA02-01.

## Technologiestack

- Java 21 / Jakarta EE
- JSF (Mojarra) – Frontend
- CDI (Weld) – Dependency Injection
- JPA (Hibernate 6.6.4) – Datenbankanbindung
- MySQL 8.0 – Datenbank
- Apache Tomcat 11 – Webserver
- Maven – Build-Management

## Voraussetzungen

- Java 21+
- Apache Tomcat 11
- MySQL 8.0
- Maven

## Datenbank einrichten

```sql
CREATE DATABASE hero2zero;
CREATE USER 'hero2zero'@'localhost' IDENTIFIED BY 'hero1234';
GRANT ALL PRIVILEGES ON hero2zero.* TO 'hero2zero'@'localhost';
```

Die Tabelle `emission_entry` wird beim ersten Start automatisch von Hibernate angelegt.

## Projekt starten

1. Repository klonen:
   ```
   git clone https://github.com/YannikMa22/ProjekteUni.git
   ```

2. Als Maven-Projekt in Eclipse importieren:
   `File → Import → Existing Maven Projects`

3. Auf Tomcat 11 deployen und Server starten

4. Anwendung aufrufen:
   ```
   http://localhost:8080/herotozero/
   ```

## Zugangsdaten

| Rolle        | Benutzername | Passwort |
|--------------|-------------|---------|
| Wissenschaftler | scientist | 1234 |
| Administrator   | admin     | 1234 |
