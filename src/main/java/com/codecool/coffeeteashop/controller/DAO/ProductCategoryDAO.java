
package com.codecool.coffeeteashop.controller.DAO;

import com.codecool.coffeeteashop.view.Input;


import java.io.IOException;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductCategoryDAO extends DataBaseDAO implements DAO{

    Input input = new Input();

    @Override
    public void select() {
        try{
            connect();
            connection.setAutoCommit(false);
            ResultSet rs = statement.executeQuery( "SELECT * FROM Categories;" );
            while ( rs.next() ) {
                int id = rs.getInt("Id_category");
                String  name = rs.getString("Name");
                String description  = rs.getString("Description");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "DESCRIPTION = " + description );
                System.out.println();
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() throws IOException {

        String newValue = input.getStringInput("Enter new value of the product category: ");
        int idCategory = input.getIntegerInput("Enter id of product category to edit: ");

        try {
            connect();
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            String q1;
            q1 = "UPDATE Categories SET Name = '" + newValue +
                    "' WHERE Id_category = '" +idCategory+ "' ";
            int x = stmt.executeUpdate(q1);

            if (x > 0)
                System.out.println("Name of the product category successfully updated");
            else
                System.out.println("ERROR OCCURRED :(");


            connection.commit();

            statement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void insertInto() throws IOException {
        String name = input.getStringInput("Enter new name of the category: ");
        String description = input.getStringInput("Enter description: ");
        try {
            connect();
            connection.setAutoCommit(false);
            String sql = String.format("INSERT INTO Categories (Name,Description) " +
                    "VALUES ('%s', '%s');", name, description);
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}