package Tabellen;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bestellposition")
public class Bestellposition {
    @DatabaseField(generatedId = true)
    private Integer bestellposition_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Bestellung bestellung;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Menuekarte gericht;

    @DatabaseField(canBeNull = false)
    private Integer anzahl;

    public Bestellposition() {
    }

    public Bestellposition(Bestellung bestellung, Menuekarte gericht, Integer anzahl) {
        this.bestellung = bestellung;
        this.gericht = gericht;
        this.anzahl = anzahl;
    }

    public Integer getBestellposition_id() {
        return bestellposition_id;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public Menuekarte getGericht() {
        return gericht;
    }

    public Integer getAnzahl() {
        return anzahl;
    }
}
