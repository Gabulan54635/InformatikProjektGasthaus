package Funktionen;

import java.sql.Date;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import Tabellen.Bestellung;
import Tabellen.Menuekarte;
import Tabellen.Mitarbeiter;
import Tabellen.Rechnung;
import Tabellen.Tisch;

public class Beispieldaten {

    public static void beispieldatenEinfuegen() {
        try {
            Dao<Menuekarte, Integer> menuekarteDao =
                    DaoManager.createDao(GasthausConnection.connectionSource, Menuekarte.class);

            Dao<Mitarbeiter, Integer> mitarbeiterDao =
                    DaoManager.createDao(GasthausConnection.connectionSource, Mitarbeiter.class);

            Dao<Tisch, Integer> tischDao =
                    DaoManager.createDao(GasthausConnection.connectionSource, Tisch.class);

            Dao<Bestellung, Integer> bestellungDao =
                    DaoManager.createDao(GasthausConnection.connectionSource, Bestellung.class);

            datenbankLeeren();

            Menuekarte schnitzel = new Menuekarte(
                    "Schnitzel",
                    "Wiener Schnitzel mit Pommes",
                    1290,
                    "Hauptspeise",
                    20,
                    true
            );

            Menuekarte pizza = new Menuekarte(
                    "Pizza",
                    "Pizza Margherita",
                    990,
                    "Hauptspeise",
                    15,
                    true
            );

            Menuekarte suppe = new Menuekarte(
                    "Suppe",
                    "Frittatensuppe",
                    490,
                    "Vorspeise",
                    25,
                    true
            );

            Menuekarte salat = new Menuekarte(
                    "Salat",
                    "Gemischter Salat",
                    590,
                    "Beilage",
                    18,
                    true
            );

            Menuekarte cola = new Menuekarte(
                    "Cola",
                    "0,5 Liter Cola",
                    350,
                    "Getränk",
                    30,
                    true
            );

            menuekarteDao.create(schnitzel);
            menuekarteDao.create(pizza);
            menuekarteDao.create(suppe);
            menuekarteDao.create(salat);
            menuekarteDao.create(cola);

            Mitarbeiter koch1 = new Mitarbeiter("Max", "Koch", true);
            Mitarbeiter koch2 = new Mitarbeiter("Anna", "Koch", true);

            mitarbeiterDao.create(koch1);
            mitarbeiterDao.create(koch2);

            Tisch tisch1 = new Tisch(1, 2);
            Tisch tisch2 = new Tisch(2, 4);
            Tisch tisch3 = new Tisch(3, 4);

            tischDao.create(tisch1);
            tischDao.create(tisch2);
            tischDao.create(tisch3);

            Date heute = new Date(System.currentTimeMillis());

            bestellungDao.create(new Bestellung(
                    tisch1,
                    koch1,
                    schnitzel,
                    heute,
                    "offen",
                    2,
                    schnitzel.getPreis() * 2
            ));

            bestellungDao.create(new Bestellung(
                    tisch2,
                    koch1,
                    pizza,
                    heute,
                    "offen",
                    3,
                    pizza.getPreis() * 3
            ));

            bestellungDao.create(new Bestellung(
                    tisch2,
                    koch2,
                    schnitzel,
                    heute,
                    "offen",
                    1,
                    schnitzel.getPreis()
            ));

            bestellungDao.create(new Bestellung(
                    tisch3,
                    koch2,
                    suppe,
                    heute,
                    "offen",
                    4,
                    suppe.getPreis() * 4
            ));

            bestellungDao.create(new Bestellung(
                    tisch3,
                    koch1,
                    cola,
                    heute,
                    "offen",
                    5,
                    cola.getPreis() * 5
            ));

            bestellungDao.create(new Bestellung(
                    tisch1,
                    koch2,
                    pizza,
                    heute,
                    "offen",
                    1,
                    pizza.getPreis()
            ));

            System.out.println("Datenbank wurde geleert und Beispieldaten wurden eingefügt.");

        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen der Beispieldaten:");
            e.printStackTrace();
        }
    }

    private static void datenbankLeeren() throws SQLException {
        TableUtils.clearTable(GasthausConnection.connectionSource, Rechnung.class);
        TableUtils.clearTable(GasthausConnection.connectionSource, Bestellung.class);
        TableUtils.clearTable(GasthausConnection.connectionSource, Menuekarte.class);
        TableUtils.clearTable(GasthausConnection.connectionSource, Mitarbeiter.class);
        TableUtils.clearTable(GasthausConnection.connectionSource, Tisch.class);
    }
}
