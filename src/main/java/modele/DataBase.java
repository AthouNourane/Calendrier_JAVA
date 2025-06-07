package modele;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
                                   LocalDateTime debut, LocalDateTime fin, int semaine) {
        String sql = """
        INSERT INTO "Reservation" ("Date", "Cours", "Niveau", "Heure", "Semaine")
        VALUES (?, ?, ?, tsrange(?, ?, '[]'), ?)
    """;

        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setString(2, cours);
            pstmt.setString(3, niveau);
            pstmt.setTimestamp(4, Timestamp.valueOf(debut));
            pstmt.setTimestamp(5, Timestamp.valueOf(fin));
            pstmt.setInt(6, semaine);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerReservation(LocalDate date,
                                     LocalDateTime debut, LocalDateTime fin) {
        String sql = """
        DELETE FROM "Reservation"
        WHERE "Date" = ?
        AND "Heure" = tsrange(?, ?, '[]')
    """;

        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setTimestamp(2, Timestamp.valueOf(debut));
            pstmt.setTimestamp(3, Timestamp.valueOf(fin));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void chargerReservationsDepuisBD(Map<Integer, Set<Reservation>> treeMap) {

        String sql = """
        SELECT "Date", "Cours", "Niveau", lower("Heure"), upper("Heure"), "Semaine"
        FROM "Reservation"
    """;

        try (Connection conn = DataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LocalDate date = rs.getDate(1).toLocalDate();
                String cours = rs.getString(2);
                String niveau = rs.getString(3);
                LocalDateTime debut = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime fin = rs.getTimestamp(5).toLocalDateTime();
                int semaine = rs.getInt(6);

                DateCalendrier dateResa = new DateCalendrier(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
                PlageHoraire plageResa = new PlageHoraire(new Horaire(debut.getHour(), debut.getMinute()), new Horaire(fin.getHour(), fin.getMinute()));

                Reservation r = new Reservation(dateResa, plageResa, cours, niveau);

                if (treeMap.containsKey(semaine)){
                    treeMap.get(semaine).add(r);
                }
                else{
                    treeMap.put(semaine, new TreeSet<>());
                    treeMap.get(semaine).add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionReservation | ExceptionHoraire e) {
            throw new RuntimeException(e);
        }
    }



}

