/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utilty.DBManager;
import entity.Note;
import entity.Tag;
import entity.User;
import java.io.Serializable;
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
public class TagDao implements Serializable {

    private DBManager dbm;
    final private String tablo_name = "tags";
    public User u;
    public int saveTag(Tag tag) {
        //1
        Statement st;
        int result = 0;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "INSERT INTO " + tablo_name + " (name) VALUES "
                    + "('" + tag.getName() + "')";
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }

        return result;
    }

    public int updateTag(Tag tag) {
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
                    + "name='" + tag.getName() + "', "
                    + "WHERE id=" + tag.getId();
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    public void deleteTag(int tag_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM " + tablo_name + " WHERE id =" + tag_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }
    }

    public ArrayList<Tag> getAllTags() {
        Statement st;
        ResultSet rs;
        ArrayList<Tag> tagList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + tablo_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tagList.add(tag);
            }
            dbm.closeConnection();    
        } catch (Exception e) {
            System.out.println("hata");
        }

        return tagList;
    }
    
    public Tag getTag(int tag_id) {
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM " + tablo_name + " WHERE id=" + tag_id;

        try {
            st = getDbm().initConn().createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                dbm.closeConnection();
                return tag;
            }
        } catch (Exception e) {
           
        }
        return null;
    }
    

    public DBManager getDbm() {
        if (this.dbm == null) {
            this.dbm = new DBManager();
        }
        return dbm;
    }

    public void setDbm(DBManager dbm) {
        this.dbm = dbm;
    }
}
