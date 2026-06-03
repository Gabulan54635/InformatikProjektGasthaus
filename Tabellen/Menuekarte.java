package Tabellen;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "menuekarte")
public class Menuekarte {
    @DatabaseField(generatedId = true)
    private Integer gericht_id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String beschreibung;

    @DatabaseField(canBeNull = false)
    private Integer preis;

    @DatabaseField(canBeNull = false)
    private String kategorie;

    @DatabaseField(canBeNull = false)
    private Integer anzahl_verfuegbar;

    @DatabaseField(canBeNull = false)
    private Boolean verfuegbar;

    public Menuekarte() {
    }

    public Menuekarte(String name, String beschreibung, Integer preis, String kategorie, Integer anzahl_verfuegbar, Boolean verfuegbar) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.kategorie = kategorie;
        this.anzahl_verfuegbar = anzahl_verfuegbar;
        this.verfuegbar = verfuegbar;
    }

    public Integer getGericht_Id() {
        return gericht_id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Integer getPreis() {
        return preis;
    }

    public String getKategorie() {
        return kategorie;
    }

    public Integer getAnzahl_verfuegbar() {
        return anzahl_verfuegbar;
    }

    public Boolean getVerfuegbar() {
        return verfuegbar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setPreis(Integer preis) {
        this.preis = preis;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public void setAnzahl_verfuegbar(Integer anzahl_verfuegbar) {
        this.anzahl_verfuegbar = anzahl_verfuegbar;
    }

    public void setVerfuegbar(Boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }
}
