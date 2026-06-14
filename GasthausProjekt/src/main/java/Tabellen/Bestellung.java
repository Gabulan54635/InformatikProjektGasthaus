package Tabellen;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bestellung")
public class Bestellung {
    @DatabaseField(generatedId = true)
    private Integer bestellung_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tisch tisch;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Mitarbeiter mitarbeiter;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Menuekarte menuekarte;

    @DatabaseField(canBeNull = false)
    private Date datum;

    @DatabaseField(canBeNull = false)
    private String status;

    @DatabaseField(canBeNull = false)
    private Integer anzahl;

    @DatabaseField(canBeNull = false)
    private Integer gesamtpreis;

    public Bestellung() {
    }

    public Bestellung(Tisch tisch, Mitarbeiter mitarbeiter, Menuekarte menuekarte, Date datum, String status, Integer anzahl,
            Integer gesamtpreis) {
        this.tisch = tisch;
        this.mitarbeiter = mitarbeiter;
        this.menuekarte = menuekarte;
        this.datum = datum;
        this.status = status;
        this.anzahl = anzahl;
        this.gesamtpreis = gesamtpreis;
    }

    public Integer getBestellung_id() {
        return bestellung_id;
    }

    public Tisch getTisch() {
        return tisch;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public Menuekarte getMenuekarte() {
        return menuekarte;
    }

    public Date getDatum() {
        return datum;
    }

    public String getStatus() {
        return status;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public Integer getGesamtpreis() {
        return gesamtpreis;
    }

    public void setTisch(Tisch tisch) {
        this.tisch = tisch;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public void setMenuekarte(Menuekarte menuekarte) {
        this.menuekarte = menuekarte;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    public void setGesamtpreis(Integer gesamtpreis) {
        this.gesamtpreis = gesamtpreis;
    }
}
