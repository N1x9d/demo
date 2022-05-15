package com.example.demo;
import com.mysql.cj.util.DnsSrv;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "кщще";
    private static final String db_driver= "C:\\Program Files (x86)\\MySQL\\Connector J 8.0\\mysql-connector-java-8.0.28.jar";
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public DB(){


    }
    static Connection dbConnection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String connectionString = "jdbc:mysql://localhost:3306/mydb";

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, user, password);

        return dbConnection;
    }

    public static List searchWords(String word) throws SQLException, ClassNotFoundException{
        String[] words = word.split(" ");
        List<WordInfo> list = new ArrayList();
        for (String wr : words) {
            try {
                Connection conn = getConnection();
                String query2 ="SELECT Fname,Word,Count,Patch from word join file on word.File_ID=file.ID where Word=?";
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString (1, wr);
                ResultSet rs = preparedStmt2.executeQuery();

                while (rs.next()) {
                    String Word = rs.getString("Word");
                    String Filename = rs.getString("Fname");
                    String Patch = rs.getString("Patch");
                    int count = rs.getInt("Count");

                    list.add(new WordInfo(Word,Filename,Patch,count));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
