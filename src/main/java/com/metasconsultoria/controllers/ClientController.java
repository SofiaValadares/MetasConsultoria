package com.metasconsultoria.controller;

import com.metasconsultoria.entities.Client;
import com.metasconsultoria.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients() throws SQLException {
        List<Client> clients = ClientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) throws SQLException {
        Client client = ClientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createClient(@RequestBody Client client) throws SQLException {
        ClientService.insertClient(client.getIdUser(), client.getIdCity());
        return ResponseEntity.ok("Cliente criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable int id, @RequestBody Client client) throws SQLException {
        client.setIdUser(id);
        ClientService.updateClient(client);
        return ResponseEntity.ok("Cliente atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) throws SQLException {
        ClientService.deleteClient(id);
        return ResponseEntity.ok("Cliente deletado com sucesso.");
    }
}
