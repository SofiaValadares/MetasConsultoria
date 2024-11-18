package com.metasconsultoria.service;

import com.metasconsultoria.entities.NextStep;
import com.metasconsultoria.repository.NextStepRepository;

import java.sql.SQLException;
import java.util.List;

public class NextStepService {

    public static void insertNextStep(NextStep nextStep) throws SQLException {
        NextStepRepository.insertInto(nextStep);
    }

    public static void updateNextStep(NextStep nextStep) throws SQLException {
        NextStepRepository.updateData(nextStep);
    }

    public static void deleteNextStep(int idNextStep) throws SQLException {
        NextStepRepository.deleteById(idNextStep);
    }

    public static List<NextStep> getAllNextSteps() throws SQLException {
        return NextStepRepository.selectAll();
    }

    public static NextStep getNextStepById(int idNextStep) throws SQLException {
        return NextStepRepository.selectById(idNextStep);
    }
}
