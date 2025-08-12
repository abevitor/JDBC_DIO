package com.example.demo.persistence;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

public class EmployeeDAO {
    public void insert(final EmployeeEntity entity){
        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
            ){
                var sql = "INSERT INTO employees(name, salary, role) VALUES ('" 
    + entity.getName() + "', "   // vírgula depois do nome e sem aspas no salário numérico
    + entity.getSalary() + ", '" // salário numérico vai direto
    + formatOfsetDate(entity.getRole()) + "')";
                statement.executeUpdate(sql);
               // System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
                if(statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());
                    
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(final EmployeeEntity entity){

    }

    public void delete(final long id){

    }

   public List<EmployeeEntity> findAll(){
    List<EmployeeEntity> entities = new ArrayList<>();
    try(var connection = ConnectionUtil.getConnection();
        var statement = connection.createStatement()){

        var resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY name");
        while(resultSet.next()) {
            var entity = new EmployeeEntity();
            entity.setId(resultSet.getLong("id"));
            entity.setName(resultSet.getString("name"));
            entity.setSalary(resultSet.getBigDecimal("salary"));
            var timestamp = resultSet.getTimestamp("role");
            if(timestamp != null){
                entity.setRole(timestamp.toInstant().atOffset(java.time.ZoneOffset.UTC));
            }

            entities.add(entity);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return entities;
}

    public EmployeeEntity findById(final long id){
         var entity = new EmployeeEntity();
    try(var connection = ConnectionUtil.getConnection();
        var statement = connection.createStatement()){

        var resultSet = statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
        if(resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
            entity.setName(resultSet.getString("name"));
            entity.setSalary(resultSet.getBigDecimal("salary"));
            var timestamp = resultSet.getTimestamp("role");
            if(timestamp != null){
                entity.setRole(timestamp.toInstant().atOffset(java.time.ZoneOffset.UTC));
            }

            
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return entity;
}  

    private String formatOfsetDate(final OffsetDateTime dateTime) {
        var utcDatetime = dateTime.withOffsetSameInstant(ZoneOffset.UTC);
        return utcDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }
    
}
