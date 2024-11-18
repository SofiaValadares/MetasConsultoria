package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.NextStep;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NextStepRepository {

    private NextStepRepository() {}

    public static void insertInto(NextStep nextStep) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "INSERT INTO Next_Steps (next_steps, fk_project) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nextStep.getNextStep());
            ps.setInt(2, nextStep.getFkProject());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void updateData(NextStep nextStep) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        
        String sql = "UPDATE Next_Steps SET next_steps = ?, fk_project = ? WHERE next_steps_pk = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nextStep.getNextStep());
            ps.setInt(2, nextStep.getFkProject());
            ps.setInt(3, nextStep.getIdNextStep());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void deleteById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM Next_Steps WHERE next_steps_pk = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static List<NextStep> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        List<NextStep> nextSteps = new ArrayList<>();
        String sql = "SELECT * FROM Next_Steps";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NextStep nextStep = NextStep.builder()
                        .idNextStep(rs.getInt("next_steps_pk"))
                        .nextStep(rs.getString("next_steps"))
                        .fkProject(rs.getInt("fk_project"))
                        .build();


                nextSteps.add(nextStep);
            }

            rs.close();
            ps.close();
        }

        conn.close();
        return nextSteps;
    }

    public static NextStep selectById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        NextStep nextStep = null;
        String sql = "SELECT * FROM Next_Steps WHERE next_steps_pk = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nextStep = new NextStep();
                    nextStep.setIdNextStep(rs.getInt("next_steps_pk"));
                    nextStep.setNextStep(rs.getString("next_steps"));
                    nextStep.setFkProject(rs.getInt("fk_project"));
                }


                rs.close();
            }

            ps.close();
        }

        conn.close();
        return nextStep;
    }
}
