package com.metasconsultoria.controllers;

import com.metasconsultoria.annotation.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericController {

    private GenericController() {

    }

    public static void insertInto(Connection conn, Class<?> clazz, Object insert) {
        try {
            String tableName = (clazz.getAnnotation(Table.class)).name();

            List<String> columnNames = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(PrimaryKey.class) || field.isAnnotationPresent(ForeignKey.class)) {
                    Column column = field.getAnnotation(Column.class);
                    columnNames.add(column.name());
                    values.add(field.get(insert));
                }
            }

            String columnsString = String.join(", ", columnNames);
            String placeholders = String.join(", ", columnNames.stream().map(col -> "?").toArray(String[]::new));

            String sql = "INSERT INTO " + tableName + " (" + columnsString + ") VALUES (" + placeholders + ")";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < values.size(); i++) {
                    stmt.setObject(i + 1, values.get(i));
                }

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByPk(Connection conn, Class<?> clazz, Object delete) {
        try {
            String tableName = (clazz.getAnnotation(Table.class)).name();

            List<String> primaryKeyName = new ArrayList<>();
            List<Object> primaryKeyValue = new ArrayList<>();


            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    Column column = field.getAnnotation(Column.class);
                    primaryKeyName.add(column.name());
                    field.setAccessible(true);
                    primaryKeyValue.add(field.get(delete));
                    break;
                }
            }

            StringBuilder sqlBuilder = new StringBuilder("DELETE FROM " + tableName + " WHERE ");

            for (int i = 0; i < primaryKeyName.size(); i++) {
                sqlBuilder.append(primaryKeyName.get(i)).append("=?");

                if (i != primaryKeyName.size() - 1) {
                    sqlBuilder.append(" AND ");
                }
            }

            String sql = sqlBuilder.toString();

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < primaryKeyValue.size(); i++) {
                    stmt.setObject(i + 1, primaryKeyValue.get(i));
                }

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateByPk(Connection conn, Class<?> clazz, Object update) {
        try {
            String tableName = (clazz.getAnnotation(Table.class)).name();

            List<String> columnNames = new ArrayList<>();
            List<Object> columnValues = new ArrayList<>();
            String primaryKeyName = null;
            Object primaryKeyValue = null;

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    Column column = field.getAnnotation(Column.class);
                    primaryKeyName = column.name();
                    primaryKeyValue = field.get(update);
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    columnNames.add(column.name());
                    columnValues.add(field.get(update));
                }
            }

            if (primaryKeyName == null || primaryKeyValue == null) {
                throw new IllegalArgumentException("Primary key not found in the class or its value is null");
            }

            StringBuilder setClause = new StringBuilder();
            for (int i = 0; i < columnNames.size(); i++) {
                setClause.append(columnNames.get(i)).append("=?");
                if (i < columnNames.size() - 1) {
                    setClause.append(", ");
                }
            }

            String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE " + primaryKeyName + "=?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < columnValues.size(); i++) {
                    stmt.setObject(i + 1, columnValues.get(i));
                }
                stmt.setObject(columnValues.size() + 1, primaryKeyValue);

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Object> selectAll(Connection conn, Class<?> clazz) {
        List<Object> select = new ArrayList<>();

        try {
            String tableName = (clazz.getAnnotation(Table.class)).name();

            String sql = "SELECT * FROM " + tableName;

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object obj = clazz.getDeclaredConstructor().newInstance();


                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);

                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation != null) {
                        String columnName = columnAnnotation.name();

                        field.set(obj, resultSet.getObject(columnName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return select;
    }

    public static Object selectById(Connection conn, Class<?> clazz, int id) {
        Object select = null;

        try {
            String tableName = (clazz.getAnnotation(Table.class)).name();

            List<String> columnNames = new ArrayList<>();
            String primaryKeyName = null;

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Column column = field.getAnnotation(Column.class);
                columnNames.add(column.name());

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    if (primaryKeyName == null) {
                        primaryKeyName = column.name();
                    } else {
                        throw new RuntimeException("Primary key already exists");
                    }
                }
            }

            String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKeyName + "=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    String columnName = columnAnnotation.name();

                    field.set(select, resultSet.getObject(columnName));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return select;
    }

}