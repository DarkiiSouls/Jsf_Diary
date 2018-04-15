package dao;

import entity.User;
import Utilty.DBManager;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao implements Serializable{

    private DBManager dbm;
    final private String tablo_name = "user";

    public int saveUser(User user) {
        //1
        Statement st;
        int result = 0;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "INSERT INTO " + tablo_name + " (username,password,email) VALUES "
                    + "('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getEmail() + "')";
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
            dbm.closeConnection();
        } catch (Exception e) {

        }

        return result;
    }
    
    public int updateUser(User user) {
        //1
        Statement st;
        int result = 0;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "UPDATE " + tablo_name + " SET "
                    + "username='"+user.getUsername()+"', "
                    + "email='"+user.getEmail()+"' "
                    + "password='"+user.getPassword()+"' "
                    + "WHERE id="+user.getId();
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
            dbm.closeConnection();
        } catch (Exception e) {

        }

        return result;
    }
    
      public void deleteUser(int user_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM "+tablo_name+" WHERE id="+user_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
            dbm.closeConnection();
        } catch (Exception e) {

        }
    }

    public User getUser(int user_id) {
        Statement st;
        ResultSet rs;
        User user = null;

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + tablo_name + " WHERE id=" + user_id;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);

            user = new User();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));                
                user.setRole(rs.getInt("role"));

            }
            dbm.closeConnection();
        } catch (Exception e) {

        }

        return user;
    }
    
    
    
    public ArrayList<User> getAllUsers() {
        Statement st;
        ResultSet rs;
        ArrayList<User> userList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + tablo_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                userList.add(user);
            }
            dbm.closeConnection();
        } catch (Exception e) {
                System.out.println("hata");
        }

        return userList;
    }

    public User getUserLogin(String username, String password) {
        Statement st;
        ResultSet rs;
        User user = null;

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + tablo_name + " WHERE username='" + username + "' AND password='" + password + "'";

        try {

            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                
            }
            dbm.closeConnection();
        } catch (Exception e) {
            System.out.println("adim 6 hata"+e.getMessage());
        }
        return user;
    }

}
