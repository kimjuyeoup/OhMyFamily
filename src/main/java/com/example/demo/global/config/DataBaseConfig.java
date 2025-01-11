package com.example.demo.global.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseConfig implements CommandLineRunner {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/";
  private static final String USER = "root";
  private static final String PASSWORD = "1234";
  private static final String DATABASE_NAME = "OMF";

  @Override
  public void run(String... args) throws Exception {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
      Statement stmt = conn.createStatement();

      String createData = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
      stmt.executeUpdate(createData);
      System.out.println("OMF 데이터베이스 생성 완료.");
    } catch (SQLException e) {
      throw new RuntimeException("데이터베이스 생성 중 오류 발생!", e);
    }
  }
}
