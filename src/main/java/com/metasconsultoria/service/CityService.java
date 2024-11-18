package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.repository.CityRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CityService {
    private static final Connection conn = ConnData.connection;

    public static void insertCity(String name, String state) throws SQLException {

        if (CityRepository.selectCityByNameAndState(conn, name, state) != null) {
            return;
        }

        City city = City.builder()
                .name(name)
                .state(state)
                .build();

        CityRepository.insetInto(conn, city);
    }

    public static void deleteCity(City city) throws SQLException {
        CityRepository.deleteById(conn, city.getIdCity());
    }

    public static void updateCity(City city) throws SQLException {

        if (CityRepository.selectCityByNameAndState(conn, city.getName(), city.getState()) != null) {
            return;
        }

        CityRepository.updateData(conn, city);
    }

    public static List<City> getAllCities () throws SQLException {
        return CityRepository.selectAll(conn);
    }
    public static City getCityById(int id) throws SQLException {
        return CityRepository.selectById(conn, id);
    }
    
}
