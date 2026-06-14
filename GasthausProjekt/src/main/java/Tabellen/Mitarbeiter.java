package Tabellen;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mitarbeiter")
public class Mitarbeiter {
    @DatabaseField(generatedId = true)
    private Integer mitarbeiter_id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String rolle;

    @DatabaseField(canBeNull = false)
    private Boolean aktiv;

    public Mitarbeiter() {
    }

    public Mitarbeiter(String name, String rolle, Boolean aktiv) {
        this.name = name;
        this.rolle = rolle;
        this.aktiv = aktiv;
    }

    public Integer getMitarbeiter_id() {
        return mitarbeiter_id;
    }

    public String getName() {
        return name;
    }

    public String getRolle() {
        return rolle;
    }

    public Boolean getAktiv() {
        return aktiv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public void setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
    }
}
