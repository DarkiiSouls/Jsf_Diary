package dao;

import entity.Note;
import Utilty.DBManager;
import bean.NoteBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;

@SessionScoped
public class NoteDao {

    private DBManager dbm;
    private final String table_name = "note";

    public int saveNote(Note note) {
        Statement st;
        int result = 0;

        String sql = "INSERT INTO " + table_name + " (title ,note_text, is_public, user_id) VALUES "
                + "('" + note.getTitle() + "','" + note.getNote_text() + "'," + note.getIsPublic() + "," + note.getUser().getId() + ")";
        System.err.println(sql);
        try {
            st = getDbm().initConn().createStatement();
            result = st.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateNote(Note note) {
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
                    + "title='"+note.getTitle()+"', "
                    + "note_text='"+note.getNote_text()+"', "
                    + "is_public='"+note.getIsPublic()+"' "
                    + "WHERE id="+note.getId();
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public void deleteNote(int note_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM "+table_name+" WHERE id="+note_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }
    }


    public Note getNote(int note_id) {
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM " + table_name + " WHERE id=" + note_id;

        try {
            st = getDbm().initConn().createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setNote_text(rs.getString("note_text"));
                note.setIsPublic(rs.getBoolean("is_public"));                
                note.setUser(new UserDao().getUser(rs.getInt("user_id")));

                return note;
            }
        } catch (Exception e) {
           
        }
        return null;
    }
        public int count() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Statement st;
        ResultSet rs;
          int count =0;
        try {
             st = getDbm().initConn().createStatement();
             rs = st.executeQuery("select count(id) from as say"+table_name);
             rs.next();
            count=rs.getInt("say");
        } catch (SQLException ex) {
            Logger.getLogger(NoteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }
    
    public ArrayList<Note> getAllNotes() {
        Statement st;
        ResultSet rs;
        ArrayList<Note> noteList = new ArrayList<>();
        
        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + table_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setNote_text(rs.getString("note_text"));
                note.setIsPublic(rs.getBoolean("is_public"));                
                note.setUser(new UserDao().getUser(rs.getInt("user_id")));
                noteList.add(note);
            }
            
        } catch (Exception e) {
                System.out.println("hata");
        }

        return noteList;
    }
    
    public ArrayList<Note> getAllUserNotes(int user_id) {
        Statement st;
        ResultSet rs;
        ArrayList<Note> noteList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + table_name + " WHERE user_id="+user_id;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setNote_text(rs.getString("note_text"));
                note.setIsPublic(rs.getBoolean("is_public"));                
                note.setUser(new UserDao().getUser(rs.getInt("user_id")));
                noteList.add(note);
            }
            
        } catch (Exception e) {
                System.out.println("hata");
        }

        return noteList;
    }

    
    public ArrayList<Note> getAllPublicNotes(int page, int lic) {
        Statement st;
        ResultSet rs;
        ArrayList<Note> noteList = new ArrayList<>();
        int start=0;
        start=(page-1)*lic;

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + table_name + " WHERE is_public=1";

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setNote_text(rs.getString("note_text"));
                note.setIsPublic(rs.getBoolean("is_public"));                
                note.setUser(new UserDao().getUser(rs.getInt("user_id")));
                noteList.add(note);
            }
            
        } catch (Exception e) {
                System.out.println("hata");
        }

        return noteList;
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
