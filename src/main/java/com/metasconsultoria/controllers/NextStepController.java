package com.metasconsultoria.controllers;

import com.google.gson.Gson;
import com.metasconsultoria.entities.NextStep;
import com.metasconsultoria.service.NextStepService;
import java.util.List;
import static spark.Spark.*;

public class NextStepController {
    public static void registerRoutes() {
        // Listar todos os próximos passos
        get("/api/next-steps", (req, res) -> {
            res.type("application/json");
            try {
                List<NextStep> steps = NextStepService.getAllNextSteps();
                return new Gson().toJson(steps);
            } catch (Exception e) {
                res.status(500);
                return "{\"error\":\"Erro ao buscar próximos passos: " + e.getMessage() + "\"}";
            }
        });

        // Criar um novo próximo passo
        post("/api/next-steps", (req, res) -> {
            res.type("application/json");
            try {
                NextStep step = new Gson().fromJson(req.body(), NextStep.class);
                NextStepService.insertNextStep(step);
                res.status(201);
                return "{\"message\":\"Próximo passo criado com sucesso!\"}";
            } catch (Exception e) {
                res.status(500);
                return "{\"error\":\"Erro ao criar próximo passo: " + e.getMessage() + "\"}";
            }
        });

        // Atualizar um próximo passo
        put("/api/next-steps/:id", (req, res) -> {
            res.type("application/json");
            try {
                int id = Integer.parseInt(req.params(":id"));
                NextStep step = new Gson().fromJson(req.body(), NextStep.class);
                step.setIdNextStep(id);
                NextStepService.updateNextStep(step);
                return "{\"message\":\"Próximo passo atualizado com sucesso!\"}";
            } catch (Exception e) {
                res.status(500);
                return "{\"error\":\"Erro ao atualizar próximo passo: " + e.getMessage() + "\"}";
            }
        });

        // Deletar um próximo passo
        delete("/api/next-steps/:id", (req, res) -> {
            res.type("application/json");
            try {
                int id = Integer.parseInt(req.params(":id"));
                NextStepService.deleteNextStep(id);
                return "{\"message\":\"Próximo passo deletado com sucesso!\"}";
            } catch (Exception e) {
                res.status(500);
                return "{\"error\":\"Erro ao deletar próximo passo: " + e.getMessage() + "\"}";
            }
        });
    }
}
