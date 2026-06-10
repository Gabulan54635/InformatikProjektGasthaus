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

    @DatabaseField(canBeNull = false)
    private Date datum;

    @DatabaseField(canBeNull = false)
    private String status;

    public Bestellung() {
    }

    public Bestellung(Tisch tisch, Mitarbeiter mitarbeiter, Date datum, String status) {
        this.tisch = tisch;
        this.mitarbeiter = mitarbeiter;
        this.datum = datum;
        this.status = status;
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

    public Date getDatum() {
        return datum;
    }

    public String getStatus() {
        return status;
    }

    public void setTisch(Tisch tisch) {
        this.tisch = tisch;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
