package com.example.demo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/jdbc_sample?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8",
    "",
    ""
);    
    }
    
}

