package bean;

import dao.CommentDao;
import entity.Comment;
import entity.Note;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CommentBean {

    private Comment c = null;
    private CommentDao cd = null;
     private List<Comment> comList = null;

    public String createComment() {
        getCd().saveComment(getC());
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }

    public String createCommentForUser(User user, Note note) {
        Comment com = getC();
        if(user.getId() == 0){
            return "/login.xhtml?faces-redirect=true";
        }
        com.setUser(user);
        com.setNoteid(note.getId());
        getCd().saveComment(com);
        setC(new Comment());
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }

    public String delete(int commnent_id) {
        
        this.cd.deleteComment(commnent_id);
        return null;
    }
          public String updateComment(){
              this.getCd().updateComment(this.c);
              this.comList=this.getCd().getAllComment();
        return "/admin/Comment/list.xhtml?faces-redirect=true";
    }
    public String update(Comment com){
        this.c=com;
        return "/admin/Comment/update.xhtml?faces-redirect=true";
    }
    public ArrayList<Comment> getAllComments (){
        ArrayList<Comment> comList = getCd().getAllComment();
        return comList;
    }
    
    public ArrayList<Comment> getAllCommentsByNoteId (int note_id){
        ArrayList<Comment> commentList = getCd().getAllCommentByNoteId(note_id);
        return commentList;
    }

    public Comment getCommentById(int comment_id) {
        c = getCd().getComment(comment_id);
        return c;
    }

    public Comment getC() {
        if (c == null) {
            c = new Comment();
        }
        return c;
    }

    public void setC(Comment c) {
        this.c = c;
    }

    public CommentDao getCd() {
        if (cd == null) {
            cd = new CommentDao();
        }
        return cd;
    }

    public void setCd(CommentDao cd) {
        this.cd = cd;
    }

}
