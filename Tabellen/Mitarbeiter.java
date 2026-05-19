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
    private Boolean aktiv;

	public Mitarbeiter() {
    }

    public Mitarbeiter(String name, Boolean aktiv) {
        this.name = name;
        this.aktiv = aktiv;
    }
    
    public String getName() {
        return name;
    }
    
    public Boolean getAktiv() {
        return aktiv;
    }
}
