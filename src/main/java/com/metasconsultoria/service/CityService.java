package com.metasconsultoria.service;

import com.metasconsultoria.entities.City;
import com.metasconsultoria.repository.CityRepository;

import java.sql.SQLException;
import java.util.List;

public class CityService {


    public static void insertCity(String name, String state) throws SQLException {

        if (CityRepository.selectCityByNameAndState(name, state) != null) {
            return;
        }

        City city = City.builder()
                .name(name)
                .state(state)
                .build();

        CityRepository.insetInto(city);
    }

    public static void deleteCity(City city) throws SQLException {
        CityRepository.deleteById(city.getIdCity());
    }

    public static void updateCity(City city) throws SQLException {

        if (CityRepository.selectCityByNameAndState(city.getName(), city.getState()) != null) {
            return;
        }

        CityRepository.updateData(city);
    }

    public static List<City> getAllCities () throws SQLException {

        return CityRepository.selectAll();
    }

    public static City getCityById(int id) throws SQLException {

        return CityRepository.selectById(id);
    }
    
}
