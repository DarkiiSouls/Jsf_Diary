
package bean;

import dao.NoteDao;
import entity.Note;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NoteBean {
    private Note n;
    private NoteDao nd;
    private List<Note> clist = null;
    private int page=1;
    private int listItemCount=5;
    private User u;
   
    
    
    public String createNote (){
        getNd().saveNote(getN());
        setN(new Note());
        return null;
    }
    
    public String createNoteForUser (User user){
        Note note = getN();
        note.setUser(user);
        getNd().saveNote(getN());
        setN(new Note());
        return "/index.xhtml?faces-redirect=true";
    }
      public String delete(int noteid) {
        this.nd.deleteNote(noteid);
         
             return "/pages/mynotes.xhtml?faces-redirect=true";
         
        
    }
       public String updateNotes(){
        this.getNd().updateNote(this.n);
        this.clist=this.getNd().getAllNotes();
        return "/index.xhtml?faces-redirect=true";
    }
    public String update(Note n){
        this.n=n;
        return "/pages/update.xhtml?faces-redirect=true";
    }
    
    public String openNote(int note_id){
        this.n = nd.getNote(note_id);
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }
    
    public Note getNoteById (int note_id){
        n = getNd().getNote(note_id);
        return n;
    }
    
    public ArrayList<Note> getAllNotes (){
        ArrayList<Note> userList = getNd().getAllNotes();
        return userList;
    }
    
    public ArrayList<Note> getAllPublicNotes (){
        ArrayList<Note> userList = getNd().getAllPublicNotes(page, listItemCount);
        return userList;
    }
    
    public ArrayList<Note> getAllUserNotes (int user_id){
        ArrayList<Note> userList = getNd().getAllUserNotes(user_id);
        return userList;
    }
     public void prev()
      {
          this.page--;
      }
      public void next()
      {
          this.page++;
      }
      public boolean hasNext() throws ClassNotFoundException, InstantiationException, IllegalAccessException
      {
          return this.page>Math.ceil(this.getNd().count()/(listItemCount));
      }
      public boolean hasPrev()
      {
          return this.page>1; 
      }
  
    public Note getN() {
        if (n==null)
            n = new Note();
        return n;
    }

    public void setN(Note n) {
        this.n = n;
    }

    public NoteDao getNd() {
        if (nd==null)
            nd = new NoteDao();
        return nd;
    }

    public void setNd(NoteDao nd) {
        this.nd = nd;
    }

    public List<Note> getClist() {
        return clist;
    }

    public void setClist(List<Note> clist) {
        this.clist = clist;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public void setListItemCount(int listItemCount) {
        this.listItemCount = listItemCount;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
    
}
