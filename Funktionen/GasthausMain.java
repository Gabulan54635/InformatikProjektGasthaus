package Funktionen;

import java.sql.Date;
import java.util.Scanner;

public class GasthausMain {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            GasthausAufbau.buildConnection();

            while (true) {
                showMenu();
                int wahl = readInt("Auswahl: ");

                switch (wahl) {
                case 1:
                    GasthausAufbau.gerichtHinzufuegen();
                    break;
                case 2:
                    GasthausAufbau.gerichtEntfernen();
                    break;
                case 3:
                    GasthausAufbau.gerichtBearbeiten();
                    break;
                case 4:
                    GasthausAufbau.tischHinzufuegen();
                    break;
                case 5:
                    GasthausAufbau.mitarbeiterHinzufuegen();
                    break;
                case 6:
                    GasthausAufbau.bestellungBasisAnlegen();
                    break;
                case 7:
                    GasthausAufbau.gerichteAnsehen();
                    break;
                case 8:
                    GasthausAufbau.tischeAnsehen();
                    break;
                case 9:
                    GasthausAufbau.mitarbeiterAnsehen();
                    break;
                case 10:
                    GasthausAufbau.bestellungenAnsehen();
                    break;
                case 0:
                    System.out.println("Programm beendet.");
                    return;
                default:
                    System.out.println("Ungültige Eingabe!");
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Starten des Programms:");
            e.printStackTrace();
        } finally {
            GasthausAufbau.closeConnection();
            sc.close();
        }
    }

    private static void showMenu() {
        System.out.println("\n--- GASTHAUS MENU ---");
        System.out.println("1) Gericht hinzufügen");
        System.out.println("2) Gericht entfernen");
        System.out.println("3) Gericht bearbeiten");
        System.out.println("4) Tisch hinzufügen");
        System.out.println("5) Mitarbeiter hinzufügen (nur Koch)");
        System.out.println("6) Bestellung Basis anlegen");
        System.out.println("7) Gerichte ansehen");
        System.out.println("8) Tische ansehen");
        System.out.println("9) Mitarbeiter ansehen");
        System.out.println("10) Bestellungen ansehen");
        System.out.println("0) Beenden");
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Zahl, bitte erneut eingeben!");
            }
        }
    }

    public static Date readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            if (input.isBlank()) {
                return null;
            }
            try {
                return Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ungültiges Datum! Format: YYYY-MM-DD");
            }
        }
    }
}
