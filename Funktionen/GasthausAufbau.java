package Funktionen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import Tabellen.Bestellung;
import Tabellen.Menuekarte;
import Tabellen.Mitarbeiter;
import Tabellen.Tisch;

public class GasthausAufbau {
    private static Dao<Menuekarte, Integer> menuekarteDao;
    private static Dao<Mitarbeiter, Integer> mitarbeiterDao;
    private static Dao<Tisch, Integer> tischDao;
    private static Dao<Bestellung, Integer> bestellungDao;
    
    static void setupDatabase() throws SQLException {
        menuekarteDao = DaoManager.createDao(GasthausConnection.connectionSource, Menuekarte.class);
        mitarbeiterDao = DaoManager.createDao(GasthausConnection.connectionSource, Mitarbeiter.class);
        tischDao = DaoManager.createDao(GasthausConnection.connectionSource, Tisch.class);
        bestellungDao = DaoManager.createDao(GasthausConnection.connectionSource, Bestellung.class);

        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Menuekarte.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Mitarbeiter.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Tisch.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Bestellung.class);
    }

    public static void gerichtHinzufuegen() {
        String name = GasthausMain.readString("Name des Gerichts: ");
        String beschreibung = GasthausMain.readString("Beschreibung: ");
        int preis = GasthausMain.readInt("Preis in Cent: ");
        String kategorie = GasthausMain.readString("Kategorie: ");
        int anzahl = GasthausMain.readInt("Anzahl verfügbar: ");

        Menuekarte gericht = new Menuekarte(name, beschreibung, preis, kategorie, anzahl, anzahl > 0);

        try {
            menuekarteDao.create(gericht);
            System.out.println("Gericht erfolgreich gespeichert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Gerichts:");
            e.printStackTrace();
        }
    }

    public static void gerichtEntfernen() {
        gerichteAnsehen();
        int id = GasthausMain.readInt("ID des Gerichts, das gelöscht werden soll: ");

        try {
            Menuekarte gericht = menuekarteDao.queryForId(id);
            if (gericht == null) {
                System.out.println("Kein Gericht mit dieser ID gefunden.");
                return;
            }

            menuekarteDao.delete(gericht);
            System.out.println("Gericht wurde gelöscht.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen des Gerichts:");
            e.printStackTrace();
        }
    }

    public static void gerichtBearbeiten() {
        gerichteAnsehen();
        int id = GasthausMain.readInt("ID des Gerichts, das bearbeitet werden soll: ");

        try {
            Menuekarte gericht = menuekarteDao.queryForId(id);
            if (gericht == null) {
                System.out.println("Kein Gericht mit dieser ID gefunden.");
                return;
            }

            String name = GasthausMain.readString("Neuer Name: ");
            String beschreibung = GasthausMain.readString("Neue Beschreibung: ");
            int preis = GasthausMain.readInt("Neuer Preis in Cent: ");
            String kategorie = GasthausMain.readString("Neue Kategorie: ");
            int anzahl = GasthausMain.readInt("Neue Anzahl verfügbar: ");

            gericht.setName(name);
            gericht.setBeschreibung(beschreibung);
            gericht.setPreis(preis);
            gericht.setKategorie(kategorie);
            gericht.setAnzahl_verfuegbar(anzahl);
            gericht.setVerfuegbar(anzahl > 0);

            menuekarteDao.update(gericht);
            System.out.println("Gericht wurde bearbeitet.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Bearbeiten des Gerichts:");
            e.printStackTrace();
        }
    }

    public static void tischHinzufuegen() {
        int tischNummer = GasthausMain.readInt("Nummer des Tisches: ");
        int plaetze = GasthausMain.readInt("Plätze (2 oder 4): ");

        if (plaetze != 2 && plaetze != 4) {
            System.out.println("Ein Tisch darf hier nur 2 oder 4 Plätze haben.");
            return;
        }

        Tisch tisch = new Tisch(tischNummer, plaetze);

        try {
            tischDao.create(tisch);
            System.out.println("Tisch erfolgreich gespeichert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Tisches:");
            e.printStackTrace();
        }
    }

    public static void mitarbeiterHinzufuegen() {
        String name = GasthausMain.readString("Name des Kochs: ");
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, "Koch", true);

        try {
            mitarbeiterDao.create(mitarbeiter);
            System.out.println("Koch erfolgreich gespeichert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Mitarbeiters:");
            e.printStackTrace();
        }
    }

    public static void bestellungAnlegen() {
        try {
        	
        } catch(Exception e) {
        	
        }
    }

    public static void gerichteAnsehen() {
        try {
            List<Menuekarte> gerichte = menuekarteDao.queryForAll();
            if (gerichte.isEmpty()) {
                System.out.println("Keine Gerichte vorhanden.");
                return;
            }

            System.out.println("\n--- Gerichte ---");
            for (Menuekarte g : gerichte) {
                System.out.println("ID: " + g.getGericht_Id()
                        + ", Name: " + g.getName()
                        + ", Preis: " + g.getPreis() + " Cent"
                        + ", Kategorie: " + g.getKategorie()
                        + ", Anzahl: " + g.getAnzahl_verfuegbar()
                        + ", Verfügbar: " + g.getVerfuegbar());
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Anzeigen der Gerichte:");
            e.printStackTrace();
        }
    }

    public static void tischeAnsehen() {
        try {
            List<Tisch> tische = tischDao.queryForAll();
            if (tische.isEmpty()) {
                System.out.println("Keine Tische vorhanden.");
                return;
            }

            System.out.println("\n--- Tische ---");
            for (Tisch t : tische) {
                System.out.println("ID: " + t.getTisch_id()
                        + ", Nummer: " + t.getTisch_nummer()
                        + ", Plätze: " + t.getPlaetze());
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Anzeigen der Tische:");
            e.printStackTrace();
        }
    }

    public static void mitarbeiterAnsehen() {
        try {
            List<Mitarbeiter> mitarbeiterListe = mitarbeiterDao.queryForAll();
            if (mitarbeiterListe.isEmpty()) {
                System.out.println("Keine Mitarbeiter vorhanden.");
                return;
            }

            System.out.println("\n--- Mitarbeiter ---");
            for (Mitarbeiter m : mitarbeiterListe) {
                System.out.println("ID: " + m.getMitarbeiter_id()
                        + ", Name: " + m.getName()
                        + ", Rolle: " + m.getRolle()
                        + ", Aktiv: " + m.getAktiv());
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Anzeigen der Mitarbeiter:");
            e.printStackTrace();
        }
    }

    public static void bestellungenAnsehen() {
        try {
            
        } catch (Exception e) {
        	
        }
    }
}
