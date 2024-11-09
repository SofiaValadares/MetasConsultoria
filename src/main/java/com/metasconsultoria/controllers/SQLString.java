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
}
