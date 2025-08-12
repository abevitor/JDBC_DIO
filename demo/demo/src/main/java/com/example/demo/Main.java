package com.example.demo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.flywaydb.core.Flyway;

import com.example.demo.persistence.EmployeeDAO;
import com.example.demo.persistence.entity.EmployeeEntity;

public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();
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

        /*var employee = new EmployeeEntity();
        employee.setName("Bill");
        employee.setSalary(new BigDecimal(3500));
        employee.setRole(OffsetDateTime.now().minusYears(20));

        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);*/

     //employeeDAO.findAll().forEach(System.out::println);

     System.out.println(employeeDAO.findById(3));
    }
}

