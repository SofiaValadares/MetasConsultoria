package com.metasconsultoria.repository;

import com.metasconsultoria.entities.NextStep;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NextStepRepository {

    private NextStepRepository() {}

    public static void insertInto(Connection conn, NextStep nextStep) throws SQLException {
        String sql = "INSERT INTO Next_Steps (next_steps, fk_project) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nextStep.getNextStep());
            ps.setInt(2, nextStep.getFkProject());

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, NextStep nextStep) throws SQLException {
        String sql = "UPDATE Next_Steps SET next_steps = ?, fk_project = ? WHERE next_steps_pk = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nextStep.getNextStep());
            ps.setInt(2, nextStep.getFkProject());
            ps.setInt(3, nextStep.getIdNextStep());

            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Next_Steps WHERE next_steps_pk = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public static List<NextStep> selectAll(Connection conn) throws SQLException {
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
        }

        return nextSteps;
    }

    public static NextStep selectById(Connection conn, int id) throws SQLException {
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
            }
        }

        return nextStep;
    }
}
