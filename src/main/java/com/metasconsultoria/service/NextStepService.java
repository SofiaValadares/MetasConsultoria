package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.NextStep;
import com.metasconsultoria.repository.NextStepRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NextStepService {
    private static final Connection conn = ConnData.connection;

    public static void insertNextStep(NextStep nextStep) throws SQLException {
        NextStepRepository.insertInto(conn, nextStep);
    }

    public static void updateNextStep(NextStep nextStep) throws SQLException {
        NextStepRepository.updateData(conn, nextStep);
    }

    public static void deleteNextStep(int idNextStep) throws SQLException {
        NextStepRepository.deleteById(conn, idNextStep);
    }

    public static List<NextStep> getAllNextSteps() throws SQLException {
        return NextStepRepository.selectAll(conn);
    }

    public static NextStep getNextStepById(int idNextStep) throws SQLException {
        return NextStepRepository.selectById(conn, idNextStep);
    }
}
