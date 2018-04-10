
package bean;

import dao.CommentDao;
import entity.Comment;
import entity.Note;
import entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CommentBean {
    private Comment c=null;
    private CommentDao cd=null;
    
    public String createComment (){
        getCd().saveComment(getC());
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }
    public String createCommentForUser (User user,Note note){
        Comment com = getC();
        note.setUser(user);
        com.setNote(note);
        getCd().saveComment(getC());
        setC(new Comment());
         return "/pages/notedetails.xhtml?faces-redirect=true";
    }
    
    public Comment getCommentById (int comment_id){
        c = getCd().getComment(comment_id);
        return c;
    }

    public Comment getC() {
        if (c==null)
            c = new Comment();
        return c;
    }

    public void setC(Comment c) {
        this.c = c;
    }

    public CommentDao getCd() {
        if (cd==null)
            cd = new CommentDao();
        return cd;
    }

    public void setCd(CommentDao cd) {
        this.cd = cd;
    }
    
    
    
}
