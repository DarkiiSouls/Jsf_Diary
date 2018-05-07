/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utilty.DBManager;
import entity.Photo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erkan
 */
public class PhotoDao {

    private DBManager dbm;

    public void insert(Photo p) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        try {

            if (dbm == null) {
                dbm = new DBManager();
            }

            PreparedStatement ps = this.dbm.initConn().prepareStatement("insert into photo (url,name,type) values(?,?,?)");
            ps.setString(1, p.getUrl());
            ps.setString(2, p.getName());
            ps.setString(3, p.getType());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Photo> getList() {
        ArrayList<Photo> photoList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        try {
            PreparedStatement ps = this.dbm.initConn().prepareStatement("select * from photo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Photo p = new Photo();
                p.setId(rs.getInt("id"));
                p.setUrl(rs.getString("url"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));

                photoList.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photoList;
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
