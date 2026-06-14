package Funktionen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import Tabellen.Bestellung;
import Tabellen.Menuekarte;
import Tabellen.Mitarbeiter;
import Tabellen.Tisch;

public class GasthausConnection {
	
    static ConnectionSource connectionSource;
    private static final Path DATABASE_DIRECTORY = Paths.get("daten");
    private static final Path DATABASE_FILE = DATABASE_DIRECTORY.resolve("gasthausdb");

    public static void buildConnection() throws Exception {
        Files.createDirectories(DATABASE_DIRECTORY);

        String databaseUrl = "jdbc:h2:" + DATABASE_FILE.toAbsolutePath().toString().replace('\\', '/');
        connectionSource = new JdbcConnectionSource(databaseUrl);
        GasthausAufbau.setupDatabase();

        System.out.println("Datenbank verbunden: " + DATABASE_FILE.toAbsolutePath() + ".mv.db");
    }

    public static void closeConnection() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
