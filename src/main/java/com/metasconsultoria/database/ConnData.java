package com.metasconsultoria.database;

import java.sql.Connection;

public class ConnData {
    public static final Connection connection = ConnectDatabase.getConnection();
}
