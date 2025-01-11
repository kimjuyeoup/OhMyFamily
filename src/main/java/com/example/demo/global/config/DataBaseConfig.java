package com.example.demo.global.config;

/*@Component
public class DataBaseConfig implements CommandLineRunner {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/";
  private static final String USER = System.getenv("MYSQL_USERNAME"); // 환경변수 MYSQL_USERNAME
  private static final String PASSWORD = System.getenv("MYSQL_PASSWORD"); // 환경변수 MYSQL_PASSWORD
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
}*/
