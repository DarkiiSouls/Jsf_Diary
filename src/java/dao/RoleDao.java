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
}