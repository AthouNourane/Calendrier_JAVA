package modele;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataBase {
    public static Connection connect() throws SQLException {
        String url = System.getenv("PG_URL");
        String user = System.getenv("PG_USER");
        String password = System.getenv("PG_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new RuntimeException("Une ou plusieurs variables d'environnement sont manquantes.");
        }

        return DriverManager.getConnection(url, user, password);
    }

    public void insererReservation(LocalDate date, String cours, String niveau,
                                   LocalDateTime debut, LocalDateTime fin) {
        String sql = """
        INSERT INTO "Reservation" ("Date", "Cours", "Niveau", "Heure")
        VALUES (?, ?, ?, tsrange(?, ?, '[]'))
    """;

        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setString(2, cours);
            pstmt.setString(3, niveau);
            pstmt.setTimestamp(4, Timestamp.valueOf(debut));
            pstmt.setTimestamp(5, Timestamp.valueOf(fin));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

