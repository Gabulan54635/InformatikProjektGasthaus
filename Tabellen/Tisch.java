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
	
	@DatabaseField(canBeNull = false)
    private String status;
	
	public Tisch() {
    }

    public Tisch(Integer tisch_nummer, Integer plaetze, String status) {
        this.tisch_nummer = tisch_nummer;
        this.plaetze = plaetze;
        this.status = status;
    }
    
    public Integer getTisch_nummer() {
        return tisch_nummer;
    }
    
    public Integer getPlaetze() {
        return plaetze;
    }
    
    public String getStatus() {
        return status;
    }
}
