package Utilty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    final private String servername = "jdbc:mysql://localhost:3306/diary";
    final private String user="root";
    final private String password="root";
    final private String driver="com.mysql.jdbc.Driver";
    
    Connection conn;
    
    public DBManager (){
        conn=null;
    }
    
    public Connection initConn () throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        if (this.conn==null){
            System.out.println(" =================== done");
            this.conn=DriverManager.getConnection(servername,user,password);
        }
        else if (this.conn.isClosed()){
            this.conn=null;
            this.conn=DriverManager.getConnection(servername,user,password);
        }
        return this.conn;
    }
    
    public void closeConnection() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
        }
    }

    public void commitConnection() {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.commit();
            }
        } catch (SQLException e) {
        }
    }

    public void rollbackConnection() {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
