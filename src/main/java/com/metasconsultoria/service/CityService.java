package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.repository.CityRepository;

import java.sql.Connection;
import java.sql.SQLException;

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
}
