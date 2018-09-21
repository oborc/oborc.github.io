//package com.example.demo;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class CreateDatabase
//{
//
//    private String DBhost = "localhost";
//    private int DBport = 3306;
//    private String username = "root";
//    private String password = "root";
//    private String DBname = "demo";
//
//    public String getDBhost() {
//        return DBhost;
//    }
//
//    public void setDBhost(String DBhost) {
//        this.DBhost = DBhost;
//    }
//
//    public int getDBport() {
//        return DBport;
//    }
//
//    public void setDBport(int DBport) {
//        this.DBport = DBport;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDBname() {
//        return DBname;
//    }
//
//    public void setDBname(String DBname) {
//        this.DBname = DBname;
//    }
//
//    public String getUrl()
//    {
//        return String.format("jdbc:mysql://%s:%d/", DBhost, DBport);
//    }
//
//
//    public void cerateDatabse()
//    {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(getUrl(),username,password);
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("create database if not exists " + DBname + "default character set utf8");
//            statement.close();
//            connection.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//}
