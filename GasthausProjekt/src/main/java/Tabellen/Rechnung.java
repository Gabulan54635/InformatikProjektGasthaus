package Tabellen;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rechnung")
public class Rechnung {
    @DatabaseField(generatedId = true)
    private Integer rechnung_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Bestellung bestellung;

    @DatabaseField(canBeNull = false)
    private Integer gesamtpreis;

    @DatabaseField(canBeNull = false)
    private String zahlungsart;

    @DatabaseField(canBeNull = false)
    private Boolean bezahlt;

    @DatabaseField(canBeNull = false)
    private Date bezahlt_am;

    public Rechnung() {
    }

    public Rechnung(Bestellung bestellung, Integer gesamtpreis, String zahlungsart, Boolean bezahlt, Date bezahlt_am) {
        this.bestellung = bestellung;
        this.gesamtpreis = gesamtpreis;
        this.zahlungsart = zahlungsart;
        this.bezahlt = bezahlt;
        this.bezahlt_am = bezahlt_am;
    }

    public Integer getRechnung_id() {
        return rechnung_id;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public Integer getGesamtpreis() {
        return gesamtpreis;
    }

    public String getZahlungsart() {
        return zahlungsart;
    }

    public Boolean getBezahlt() {
        return bezahlt;
    }

    public Date getBezahlt_am() {
        return bezahlt_am;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public void setGesamtpreis(Integer gesamtpreis) {
        this.gesamtpreis = gesamtpreis;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public void setBezahlt(Boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    public void setBezahlt_am(Date bezahlt_am) {
        this.bezahlt_am = bezahlt_am;
    }
}
