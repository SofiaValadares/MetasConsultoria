package com.metasconsultoria.controllers;

import java.util.List;

public class SQLString {

    private SQLString(){

    }

    public static String insertInto(String tableName, List<String> columns){
        StringBuilder builder = new StringBuilder();

        builder.append("INSERT INTO ").append(tableName).append(" (");

        for (int i = 0; i < columns.size(); i++) {
            builder.append(columns.get(i));

            if (i < columns.size() - 1) {
                builder.append(",");
            }
        }

        builder.append(") VALUES (");

        for (int i = 0; i < columns.size(); i++) {
            builder.append("?");

            if (i < columns.size() - 1) {
                builder.append(",");
            }
        }

        builder.append(");");

        return builder.toString();
    }


    public static String deleteFrom(String tableName, List<String> where){
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ").append(tableName).append(" ");

        for (int i = 0; i < where.size(); i++) {
            builder.append("WHERE ").append(where.get(i));

            if (i < where.size() - 1) {
                builder.append(" and ");
            }
        }

        return builder.toString();
    }
}
