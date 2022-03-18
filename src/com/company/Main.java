package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    static Connection con;
    static Statement stmt;

    public static void main(String[] args) throws FileNotFoundException {



        File file = new File("imdb-date.csv");
        Scanner scanner = new Scanner(file);
        Scanner userInput = new Scanner(System.in);

        System.out.println("Write Username");
        //String username = userInput.next();

        System.out.println("Write Password");
        //String password = userInput.next();

        System.out.println("Write database name");

        String databaseName = userInput.next();

        //CREATE DATABASE
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "m00nice", "Totodile2305");

            stmt = con.createStatement();

            stmt.executeUpdate("CREATE SCHEMA '"+databaseName+"'");
        } catch (Exception e){ e.printStackTrace();}

        //CREATE TABLE
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, "m00nice", "Totodile2305");
            System.out.println("Write table name");
            String tableName = userInput.next();
            boolean running = true;
            while(running){
                System.out.println("Indholder første linje af filen column titler?\n 1 for JA\n 2 for NEJ");

                switch (userInput.next()){
                    case "1" -> {
                        String[] columnName = scanner.nextLine().split(";");

                        String[] columnDatatype = new String[columnName.length];
                        for (int i = 0; i < columnName.length; i++) {
                            System.out.println("Write"+i+". column datatypes and storage");
                            String NewColumnDatatype = userInput.next().toUpperCase();
                            columnDatatype[i] = NewColumnDatatype;
                        }
                        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE '" + databaseName + "'.'" + tableName + "'(");
                        for (int i = 0; i < columnName.length; i++) {
                            stringBuilder.append("'" + columnName[i] + "'" + columnDatatype[i] + ",");
                        }
                        stringBuilder.append(")");

                        stmt = con.createStatement();

                        stmt.executeUpdate(String.valueOf(stringBuilder));

                        running = false;
                    }
                    case "2" -> {
                        String[] columnAmount = scanner.nextLine().split(";");
                        scanner.reset();
                        String[] columnName = new String[columnAmount.length];
                        for (int i = 0; i < columnAmount.length; i++) {
                            System.out.println("Write "+i+". column name");
                            String newColumnName = userInput.next();
                            columnName[i] = newColumnName;
                        }
                        String[] columnDatatype = new String[columnAmount.length];
                        for (int i = 0; i < columnAmount.length; i++) {
                            System.out.println("Write"+i+". column datatypes and storage");
                            String NewColumnDatatype = userInput.next().toUpperCase();
                            columnDatatype[i] = NewColumnDatatype;
                        }
                        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE '" + databaseName + "'.'" + tableName + "'(");
                        for (int i = 0; i < columnAmount.length; i++) {
                            stringBuilder.append("'" + columnName[i] + "'" + columnDatatype[i] + ",");
                        }
                        stringBuilder.append(")");

                        stmt = con.createStatement();

                        stmt.executeUpdate(String.valueOf(stringBuilder));

                        running = false;
                    }
                }
            }
        } catch (Exception e){ e.printStackTrace();}

        //ADD DATA TO TABLE


    }
}
