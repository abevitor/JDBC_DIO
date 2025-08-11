package com.example.demo;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbc_sample?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8",
                        "root",
                        "Vitriz3003!")
                .load();

        // Limpa o histórico de migrations com erro
        flyway.repair();

        // Executa as migrations
        flyway.migrate();
    }
}

