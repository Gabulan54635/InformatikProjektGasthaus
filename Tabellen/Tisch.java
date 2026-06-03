package Tabellen;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tisch")
public class Tisch {
    @DatabaseField(generatedId = true)
    private Integer tisch_id;

    @DatabaseField(canBeNull = false)
    private Integer tisch_nummer;

    @DatabaseField(canBeNull = false)
    private Integer plaetze;

    public Tisch() {
    }

    public Tisch(Integer tisch_nummer, Integer plaetze) {
        this.tisch_nummer = tisch_nummer;
        this.plaetze = plaetze;
    }

    public Integer getTisch_id() {
        return tisch_id;
    }

    public Integer getTisch_nummer() {
        return tisch_nummer;
    }

    public Integer getPlaetze() {
        return plaetze;
    }

    public void setTisch_nummer(Integer tisch_nummer) {
        this.tisch_nummer = tisch_nummer;
    }

    public void setPlaetze(Integer plaetze) {
        this.plaetze = plaetze;
    }
}
