package com.metasconsultoria.controller;

import com.metasconsultoria.entities.NextStep;
import com.metasconsultoria.service.NextStepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/nextsteps")
public class NextStepController {

    @GetMapping("/")
    public ResponseEntity<List<NextStep>> getAllNextSteps() throws SQLException {
        List<NextStep> nextSteps = NextStepService.getAllNextSteps();
        return ResponseEntity.ok(nextSteps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NextStep> getNextStepById(@PathVariable int id) throws SQLException {
        NextStep nextStep = NextStepService.getNextStepById(id);
        if (nextStep != null) {
            return ResponseEntity.ok(nextStep);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createNextStep(@RequestBody NextStep nextStep) throws SQLException {
        NextStepService.insertNextStep(nextStep);
        return ResponseEntity.ok("Próximo passo criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNextStep(@PathVariable int id, @RequestBody NextStep nextStep) throws SQLException {
        nextStep.setIdNextStep(id);
        NextStepService.updateNextStep(nextStep);
        return ResponseEntity.ok("Próximo passo atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNextStep(@PathVariable int id) throws SQLException {
        NextStepService.deleteNextStep(id);
        return ResponseEntity.ok("Próximo passo deletado com sucesso.");
    }
}
