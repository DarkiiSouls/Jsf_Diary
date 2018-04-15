/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utilty.DBManager;
import entity.Note;
import entity.Reminder;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DarkI
 */
@SessionScoped
public class ReminderDao {
    
      private DBManager dbm;
    private final String table_name = "remander";
    
        public int saveRemander(Reminder rmd) {
        Statement st;
        int result = 0;

        String sql = "INSERT INTO " + table_name + " (remander ,date,user_id) VALUES "
                + "('" + rmd.getRemander() + "','" + rmd.getDate() + "," + rmd.getUser().getId() + ")";
        System.err.println(sql);
        try {
            st = getDbm().initConn().createStatement();
            result = st.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
            public int updateRemander(Reminder rmd) {
        //1
        Statement st;
        int result = 0;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "UPDATE " + table_name + " SET "
                    + "remander='"+rmd.getRemander()+"', "
                    + "date='"+rmd.getDate()+"', "
                    + "WHERE id="+rmd.getId();
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
                
    public void deleteRemander(int rmd_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM "+table_name+" WHERE id="+rmd_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }
    }
        public ArrayList<Reminder> getRemander() {
        Statement st;
        ResultSet rs;
        ArrayList<Reminder> rmdList = new ArrayList<>();
        
        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + table_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Reminder rmd = new Reminder();
                rmd.setId(rs.getInt("id"));
                rmd.setRemander(rs.getString("remander"));
                rmd.setDate(rs.getDate("date"));              
                rmd.setUser(new UserDao().getUser(rs.getInt("user_id")));
                rmdList.add(rmd);
            }
            
        } catch (Exception e) {
                System.out.println("hata");
        }

        return rmdList;
    }


    public DBManager getDbm() {
        return dbm;
    }

    public void setDbm(DBManager dbm) {
        this.dbm = dbm;
    }
    
    
}
