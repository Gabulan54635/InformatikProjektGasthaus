package Funktionen;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import Tabellen.Bestellung;
import Tabellen.Menuekarte;
import Tabellen.Mitarbeiter;
import Tabellen.Rechnung;
import Tabellen.Tisch;

public class GasthausAufbau {
    private static Dao<Menuekarte, Integer> menuekarteDao;
    private static Dao<Mitarbeiter, Integer> mitarbeiterDao;
    private static Dao<Tisch, Integer> tischDao;
    private static Dao<Bestellung, Integer> bestellungDao;
    private static Dao<Rechnung, Integer> rechnungDao;
    
    static int nummer = 0;

    static void setupDatabase() throws SQLException {
        menuekarteDao = DaoManager.createDao(GasthausConnection.connectionSource, Menuekarte.class);
        mitarbeiterDao = DaoManager.createDao(GasthausConnection.connectionSource, Mitarbeiter.class);
        tischDao = DaoManager.createDao(GasthausConnection.connectionSource, Tisch.class);
        bestellungDao = DaoManager.createDao(GasthausConnection.connectionSource, Bestellung.class);
        rechnungDao = DaoManager.createDao(GasthausConnection.connectionSource, Rechnung.class);

        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Menuekarte.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Mitarbeiter.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Tisch.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Bestellung.class);
        TableUtils.createTableIfNotExists(GasthausConnection.connectionSource, Rechnung.class);
    }

    public static void gerichtHinzufuegen() {
        String name = GasthausMain.readString("Name des Gerichts: ", "Mustername");
        String beschreibung = GasthausMain.readString("Beschreibung: ", "Musterbeschreibung: :)");
        int preis = GasthausMain.readInt("Preis in Cent: ", 0);
        String kategorie = GasthausMain.readString("Kategorie: ", "Musterkategorie");
        int anzahl = GasthausMain.readInt("Anzahl verfügbar: ", 0);

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
        int id = GasthausMain.readInt("ID des Gerichts, das gelöscht werden soll: ", 0);

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
        int id = GasthausMain.readInt("ID des Gerichts, das bearbeitet werden soll: ", 0);

        try {
            Menuekarte gericht = menuekarteDao.queryForId(id);
            if (gericht == null) {
                System.out.println("Kein Gericht mit dieser ID gefunden.");
                return;
            }

            String name = GasthausMain.readString("Neuer Name: ", "Mustername");
            String beschreibung = GasthausMain.readString("Neue Beschreibung: ", "Musterbeschreibung: :)");
            int preis = GasthausMain.readInt("Neuer Preis in Cent: ", 0);
            String kategorie = GasthausMain.readString("Neue Kategorie: ", "Musterkategorie");
            int anzahl = GasthausMain.readInt("Neue Anzahl verfügbar: ", 0);

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
        int tischNummer = GasthausMain.readInt("Nummer des Tisches: ", nummer);
        nummer++;
        int plaetze = GasthausMain.readInt("Plätze (2 oder 4): ", 2);

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
        String name = GasthausMain.readString("Name des Kochs: ", "Mustername");
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, "Koch", true);

        try {
            mitarbeiterDao.create(mitarbeiter);
            System.out.println("Koch erfolgreich gespeichert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Mitarbeiters:");
            e.printStackTrace();
        }
    }

    public static boolean anzahlWegzaehlen(Menuekarte menuekarte, int bestellteAnzahl) {
        if (bestellteAnzahl <= 0) {
            System.out.println("Die Anzahl muss größer als 0 sein.");
            return false;
        }

        if (!menuekarte.getVerfuegbar() || menuekarte.getAnzahl_verfuegbar() <= 0) {
            System.out.println("Dieses Essen ist ausverkauft.");
            return false;
        }

        if (bestellteAnzahl > menuekarte.getAnzahl_verfuegbar()) {
            System.out.println("Es gibt nicht genug Essen dafür.");
            return false;
        }

        int neueAnzahl = menuekarte.getAnzahl_verfuegbar() - bestellteAnzahl;

        menuekarte.setAnzahl_verfuegbar(neueAnzahl);
        menuekarte.setVerfuegbar(neueAnzahl > 0);

        try {
            menuekarteDao.update(menuekarte);
            return true;
        } catch (SQLException e) {
            System.out.println("Fehler beim Wegzählen der Anzahl:");
            e.printStackTrace();
            return false;
        }
    }

    public static void bestellungAnlegen() {
        try {
            System.out.println("\n--- Tisch ---");
            List<Tisch> tische = tischDao.queryForAll();

            if (tische.isEmpty()) {
                System.out.println("Es sind noch keine Tische vorhanden.");
                return;
            }

            for (Tisch t : tische) {
                System.out.println("ID: " + t.getTisch_id()
                        + ", Tischnummer: " + t.getTisch_nummer()
                        + ", Plätze: " + t.getPlaetze());
            }

            int tischID = GasthausMain.readInt("TischID: ", 0);
            Tisch tisch = tischDao.queryForId(tischID);

            if (tisch == null) {
                System.out.println("Keinen Tisch mit dieser ID gefunden.");
                return;
            }

            List<Menuekarte> menuekarten = menuekarteDao.queryForAll();

            if (menuekarten.isEmpty()) {
                System.out.println("Es ist leider noch kein Essen verfügbar.");
                return;
            }

            System.out.println("\n--- Gerichte ---");

            for (Menuekarte g : menuekarten) {
                System.out.println("ID: " + g.getGericht_Id()
                        + ", Name: " + g.getName()
                        + ", Beschreibung: " + g.getBeschreibung()
                        + ", Preis: " + centZuEuro(g.getPreis())
                        + ", Kategorie: " + g.getKategorie()
                        + ", Anzahl: " + g.getAnzahl_verfuegbar()
                        + ", Verfügbar: " + g.getVerfuegbar());
            }

            int menuekarteID = GasthausMain.readInt("EssensID: ", 0);
            Menuekarte menuekarte = menuekarteDao.queryForId(menuekarteID);

            if (menuekarte == null) {
                System.out.println("Kein Essen mit dieser ID gefunden.");
                return;
            }

            int menuekarteAnzahl = GasthausMain.readInt("Anzahl dieser Speise: ", 1);

            List<Mitarbeiter> mitarbeiterer = mitarbeiterDao.queryForAll();

            if (mitarbeiterer.isEmpty()) {
                System.out.println("Es ist kein Mitarbeiter angestellt.");
                return;
            }

            System.out.println("\n--- Mitarbeiter ---");

            for (Mitarbeiter m : mitarbeiterer) {
                System.out.println("ID: " + m.getMitarbeiter_id()
                        + ", Name: " + m.getName()
                        + ", Rolle: " + m.getRolle()
                        + ", Arbeitet gerade: " + m.getAktiv());
            }

            int mitarbeiterID = GasthausMain.readInt("MitarbeiterID: ", 0);
            Mitarbeiter mitarbeiter = mitarbeiterDao.queryForId(mitarbeiterID);

            if (mitarbeiter == null) {
                System.out.println("Kein Mitarbeiter mit dieser ID gefunden.");
                return;
            }

            boolean erfolgreich = anzahlWegzaehlen(menuekarte, menuekarteAnzahl);

            if (!erfolgreich) {
                return;
            }

            int gesamtpreis = menuekarte.getPreis() * menuekarteAnzahl;
            Date datum = new Date(System.currentTimeMillis());
            Bestellung bestellung = new Bestellung(tisch, mitarbeiter, menuekarte, datum, "offen", menuekarteAnzahl, gesamtpreis);

            bestellungDao.create(bestellung);

            System.out.println("Bestellung wurde gespeichert.");
            System.out.println("Bestell-ID: " + bestellung.getBestellung_id());
            System.out.println("Gesamtpreis: " + centZuEuro(gesamtpreis));

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufnehmen der Bestellung:");
            e.printStackTrace();
        }
    }

    public static void rechnungErstellen() {
        try {
            bestellungenAnsehen();
            int bestellungID = GasthausMain.readInt("Bestell-ID für die Rechnung: ", 0);

            Bestellung bestellung = bestellungDao.queryForId(bestellungID);

            if (bestellung == null) {
                System.out.println("Keine Bestellung mit dieser ID gefunden.");
                return;
            }

            if ("bezahlt".equalsIgnoreCase(bestellung.getStatus())) {
                System.out.println("Diese Bestellung wurde bereits bezahlt.");
                return;
            }

            String zahlungsart = GasthausMain.readString("Zahlungsart (Bar/Karte): ", "Bar");
            Date bezahltAm = new Date(System.currentTimeMillis());

            Rechnung rechnung = new Rechnung(bestellung, bestellung.getGesamtpreis(), zahlungsart, true, bezahltAm);
            rechnungDao.create(rechnung);

            bestellung.setStatus("bezahlt");
            bestellungDao.update(bestellung);

            System.out.println("Rechnung wurde erstellt.");
            System.out.println("Rechnungs-ID: " + rechnung.getRechnung_id());
            System.out.println("Gesamtpreis: " + centZuEuro(rechnung.getGesamtpreis()));
            System.out.println("Zahlungsart: " + rechnung.getZahlungsart());
            System.out.println("Bezahlt am: " + rechnung.getBezahlt_am());
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Rechnung:");
            e.printStackTrace();
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
                        + ", Beschreibung: " + g.getBeschreibung()
                        + ", Preis: " + centZuEuro(g.getPreis())
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
            List<Bestellung> bestellungen = bestellungDao.queryForAll();

            if (bestellungen.isEmpty()) {
                System.out.println("Keine Bestellungen vorhanden.");
                return;
            }

            System.out.println("\n--- Bestellungen ---");

            for (Bestellung b : bestellungen) {
                System.out.println("ID: " + b.getBestellung_id()
                        + ", Tisch: " + b.getTisch().getTisch_nummer()
                        + ", Essen: " + b.getMenuekarte().getName()
                        + ", Anzahl: " + b.getAnzahl()
                        + ", Preis: " + centZuEuro(b.getGesamtpreis())
                        + ", Mitarbeiter: " + b.getMitarbeiter().getName()
                        + ", Datum: " + b.getDatum()
                        + ", Status: " + b.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Anzeigen der Bestellungen:");
            e.printStackTrace();
        }
    }

    public static void rechnungenAnsehen() {
        try {
            List<Rechnung> rechnungen = rechnungDao.queryForAll();

            if (rechnungen.isEmpty()) {
                System.out.println("Keine Rechnungen vorhanden.");
                return;
            }

            System.out.println("\n--- Rechnungen ---");

            for (Rechnung r : rechnungen) {
                Bestellung b = r.getBestellung();
                System.out.println("ID: " + r.getRechnung_id()
                        + ", Bestell-ID: " + b.getBestellung_id()
                        + ", Tisch: " + b.getTisch().getTisch_nummer()
                        + ", Essen: " + b.getMenuekarte().getName()
                        + ", Anzahl: " + b.getAnzahl()
                        + ", Gesamtpreis: " + centZuEuro(r.getGesamtpreis())
                        + ", Zahlungsart: " + r.getZahlungsart()
                        + ", Bezahlt: " + r.getBezahlt()
                        + ", Bezahlt am: " + r.getBezahlt_am());
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Anzeigen der Rechnungen:");
            e.printStackTrace();
        }
    }
    
    public static void leaderboardAnsehen() {
        try {
            List<Bestellung> bestellungen = bestellungDao.queryForAll();

            if (bestellungen.isEmpty()) {
                System.out.println("Es gibt noch keine Bestellungen für das Leaderboard.");
                return;
            }

            ArrayList<String> gerichtNamen = new ArrayList<>();
            ArrayList<Integer> anzahlBestellt = new ArrayList<>();

            for (Bestellung b : bestellungen) {
                String name = b.getMenuekarte().getName();
                int anzahl = b.getAnzahl();

                boolean gefunden = false;

                for (int i = 0; i < gerichtNamen.size(); i++) {
                    if (gerichtNamen.get(i).equals(name)) {
                        int neueAnzahl = anzahlBestellt.get(i) + anzahl;
                        anzahlBestellt.set(i, neueAnzahl);
                        gefunden = true;
                        break;
                    }
                }

                if (!gefunden) {
                    gerichtNamen.add(name);
                    anzahlBestellt.add(anzahl);
                }
            }

            for (int i = 0; i < anzahlBestellt.size() - 1; i++) {
                for (int j = i + 1; j < anzahlBestellt.size(); j++) {
                    if (anzahlBestellt.get(j) > anzahlBestellt.get(i)) {
                        int tempAnzahl = anzahlBestellt.get(i);
                        anzahlBestellt.set(i, anzahlBestellt.get(j));
                        anzahlBestellt.set(j, tempAnzahl);

                        String tempName = gerichtNamen.get(i);
                        gerichtNamen.set(i, gerichtNamen.get(j));
                        gerichtNamen.set(j, tempName);
                    }
                }
            }

            System.out.println("\n--- LEADERBOARD ---");

            for (int i = 0; i < gerichtNamen.size(); i++) {
                System.out.println((i + 1) + ". " + gerichtNamen.get(i)
                        + " - " + anzahlBestellt.get(i) + " mal bestellt");
            }

        } catch (Exception e) {
            System.out.println("Fehler beim Anzeigen des Leaderboards:");
            e.printStackTrace();
        }
    }

    private static String centZuEuro(int cent) {
        int euro = cent / 100;
        int rest = Math.abs(cent % 100);
        return euro + "," + (rest < 10 ? "0" + rest : rest) + " €";
    }
}
