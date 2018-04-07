package dao;

import entity.Comment;
import Utilty.DBManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDao {

    private DBManager dbm;
    private final String table_name = "comment";

    public int saveComment(Comment comment) {
        Statement st;
        int result = 0;
        

        String sql = "INSERT INTO " + table_name + " (comment_text,user_id,note_id) VALUES "
                + "('" + comment.getComment_text() + "'," + comment.getUser().getId() + "," + comment.getNoteid() + ")";
        System.out.println(sql);
        try {
            st = getDbm().initConn().createStatement();
            result = st.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int updateComment(Comment comment) {
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
                    + "note_text='"+comment.getComment_text()+"', "
                    + "WHERE id="+comment.getId();
            //4Comment
            st = dbm.initConn().createStatement();
            result = st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }

        return result;
    }
    
      public void deleteComment(int comment_id) {
        //1
        Statement st;

        try {
            //2
            if (dbm == null) {
                dbm = new DBManager();
            }
            //3
            String sqlKomudu = "DELETE FROM "+table_name+" WHERE id="+comment_id;
            //4
            st = dbm.initConn().createStatement();
            st.executeUpdate(sqlKomudu);
        } catch (Exception e) {

        }
    }

    public Comment getComment(int comment_id) {
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM " + table_name + " WHERE id=" + comment_id;

        try {
            st = getDbm().initConn().createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment_text(rs.getString("comment_text"));
                comment.setUser(new UserDao().getUser(rs.getInt("user_id")));                
                comment.setNote(new NoteDao().getNote(rs.getInt("note_id")));

                return comment;
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
