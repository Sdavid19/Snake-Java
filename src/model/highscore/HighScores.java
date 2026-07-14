/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.highscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 *
 * @author bli
 */
public class HighScores {

    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;

    public HighScores(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        Properties connectionProps = new Properties();
        // Add new user -> MySQL workbench (Menu: Server / Users and priviliges)
        //                             Tab: Administrative roles -> Check "DBA" option
        connectionProps.put("user", "root");
        connectionProps.put("password", "progtech");
        connectionProps.put("serverTimezone", "UTC");
        String dbURL = "jdbc:mysql://localhost:3306/Highscores";
        connection = DriverManager.getConnection(dbURL, connectionProps);


        String insertQuery = "INSERT INTO HIGHSCORES (TIMESTAMP, NAME, SCORE) VALUES (?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES ORDER BY score";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            highScores.add(new HighScore(name, score));
        }
        sortHighScores(highScores);
        return highScores;
    }

    public void putHighScore(String name, int score) throws SQLException {
        // Ellenőrizzük, hogy létezik-e már a név
        String selectQuery = "SELECT SCORE FROM HIGHSCORES WHERE NAME=?";
        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(1, name);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            int existingScore = rs.getInt("SCORE");
            if (score > existingScore) {
                String updateQuery = "UPDATE HIGHSCORES SET SCORE=?, TIMESTAMP=? WHERE NAME=?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setInt(1, score);
                updateStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                updateStmt.setString(3, name);
                updateStmt.executeUpdate();
            }
        } else {
            insertScore(name, score);
        }
    }

    /**
     * Sort the high scores in descending order.
     * @param highScores
     */
    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    private void insertScore(String name, int score) throws SQLException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        insertStatement.setTimestamp(1, ts);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, score);
        insertStatement.executeUpdate();
    }

}
