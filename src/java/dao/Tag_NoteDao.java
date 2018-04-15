/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Utilty.DBManager;
import entity.Note;
import entity.Tag;
import entity.Tag_Note;
import entity.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DarkI
 */
@SessionScoped
public class Tag_NoteDao implements Serializable {

    private DBManager dbm;
    final private String table_name = "tag_note";
    final private String first_table_name = "tags";
    final private String second_table_name = "note";

    public User u;
    public int saveTagNote(Tag_Note tagNote) {
        //1
        Statement st;
        int result = 0;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "INSERT INTO " + table_name + " (note_id, tag_id) VALUES "
                    + "('" + tagNote.getNote().getId()+ "','"+ tagNote.getTag().getId()+"')";
            //4
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }

        return result;
    }

//    public int updateTag(Tag tag) {
//        //1
//        Statement st;
//        int result = 0;
//
//        try {
//            //2
//            if (dbm == null) {
//                dbm = new DBManager();
//            }
//            //3
//            String sqlKomudu = "UPDATE " + tablo_name + " SET "
//                    + "name='" + tag.getName() + "', "
//                    + "WHERE id=" + tag.getId();
//            //4
//            st = dbm.initConn().createStatement();
//            result = st.executeUpdate(sqlKomudu);
//        } catch (Exception e) {
//
//        }
//
//        return result;
//    }

//    public void deleteTag(int tag_id) {
//        //1
//        Statement st;
//
//        try {
//            //2
//            if (dbm == null) {
//                dbm = new DBManager();
//            }
//            //3
//            String sqlKomudu = "DELETE FROM " + tablo_name + " WHERE id=" + tag_id;
//            //4
//            st = dbm.initConn().createStatement();
//            st.executeUpdate(sqlKomudu);
//        } catch (Exception e) {
//
//        }
//    }

    public ArrayList<Tag_Note> getAllTagNotes() {
        Statement st;
        ResultSet rs;
        ArrayList<Tag_Note> tagNoteList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT * FROM " + table_name;

        try {
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Tag_Note tagNote = new Tag_Note();
                tagNote.setId(rs.getInt("id"));
                tagNote.setNote(new NoteDao().getNote(rs.getInt("note_id")));                
                tagNote.setTag(new TagDao().getTag(rs.getInt("tag_id")));
                tagNoteList.add(tagNote);
            }

        } catch (Exception e) {
            System.out.println("hata");
        }

        return tagNoteList;
    }
    
    public ArrayList<Note> getAllNotesByTag(int tag_id) {
        Statement st;
        ResultSet rs;
        ArrayList<Note> noteList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }
        
        String sqlKomudu = "SELECT note.* FROM note " +
                           "INNER JOIN tag_note " +
                           "ON note.id = tag_note.note_id " +
                           "WHERE tag_note.tag_id = "+ tag_id;
        try {
            System.out.println(sqlKomudu);
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
            System.out.println("hata "+ e.getMessage());
        }

        return noteList;
    }
    public ArrayList<Tag> getAllTagsByNote(int note_id) {
        Statement st;
        ResultSet rs;
        ArrayList<Tag> tagList = new ArrayList<>();

        if (dbm == null) {
            dbm = new DBManager();
        }

        String sqlKomudu = "SELECT tags.* FROM tags " +
                           "INNER JOIN tag_note " +
                           "ON tags.id = tag_note.tag_id " +
                           "WHERE tag_note.note_id = "+ note_id;
System.out.println(sqlKomudu);
        try {
            
            st = dbm.initConn().createStatement();
            rs = st.executeQuery(sqlKomudu);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tagList.add(tag);
            }

        } catch (Exception e) {
            System.out.println("hata "+e.getMessage());
        }

        return tagList;
    }

}
