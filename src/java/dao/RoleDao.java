package dao;

import Utilty.DBManager;
import entity.Note;
import entity.Role;
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
public class RoleDao implements Serializable {

    private DBManager dbm;
    final private String tablo_name = "role";
    public User u;
    public int saveRole(Role role) {
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
                    + "('" + role.getName() + "')";
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }

        return result;
    }

    public int updateRole(Role role) {
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
                    + "name='" + role.getName() + "', "
                    + "WHERE id=" + role.getId();
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    public void deleteRole(int role_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM " + tablo_name + " WHERE id =" + role_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }
    }
    public ArrayList<Role> getAllRole() {
        Statement st;
        ResultSet rs;
        ArrayList<Role> roleList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + tablo_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roleList.add(role);
            }
            dbm.closeConnection();    
        } catch (Exception e) {
            System.out.println("hata");
        }

        return roleList;
    }
    public Role getRole(int role_id) {
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM " + tablo_name + " WHERE id=" + role_id;

        try {
            st = getDbm().initConn().createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                dbm.closeConnection();
                return role;
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