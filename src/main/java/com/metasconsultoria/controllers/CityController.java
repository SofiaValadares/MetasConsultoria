package com.metasconsultoria.controller;

import com.metasconsultoria.entities.City;
import com.metasconsultoria.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping("/")
    public ResponseEntity<List<City>> getAllCities() throws SQLException {
        List<City> cities = CityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable int id) throws SQLException {
        City city = CityService.getCityById(id);
        if (city != null) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createCity(@RequestBody City city) throws SQLException {
        CityService.insertCity(city.getName(), city.getState());
        return ResponseEntity.ok("Cidade criada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCity(@PathVariable int id, @RequestBody City city) throws SQLException {
        city.setIdCity(id);
        CityService.updateCity(city);
        return ResponseEntity.ok("Cidade atualizada com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable int id) throws SQLException {
        City city = CityService.getCityById(id);
        if (city != null) {
            CityService.deleteCity(city);
            return ResponseEntity.ok("Cidade deletada com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
