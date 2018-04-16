package bean;

import dao.NoteDao;
import dao.TagDao;
import dao.Tag_NoteDao;
import entity.Note;
import entity.Tag;
import entity.Tag_Note;
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

    private User u;
    private List<String> tagsOfNote;

    public String createNote() {
        getNd().saveNote(getN());
        setN(new Note());
        return null;
    }

    public String createNoteForUser(User user) {
        Note note = getN();
        note.setUser(user);
        int id = getNd().saveNote(getN());
        List<Note> allnotes = getAllNotes();
        Note lastNote = allnotes.get(allnotes.size()-1);
        if(tagsOfNote != null && tagsOfNote.size() > 0){
            Tag_NoteDao td = new Tag_NoteDao();
            for (int i = 0; i < tagsOfNote.size(); i++){
                Tag_Note tagNote = new Tag_Note();
                tagNote.setNote(lastNote);
                Tag t = new TagDao().getTag(Integer.parseInt(tagsOfNote.get(i)));
                tagNote.setTag(t);
                td.saveTagNote(tagNote);
            }
        }
        setN(new Note());
        tagsOfNote = new ArrayList<String>();
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String addTagToNote(Tag tag){
        Note note = getN();
        Tag_Note tagNote = new Tag_Note();
        tagNote.setNote(note);
        tagNote.setTag(tag);
        new Tag_NoteDao().saveTagNote(tagNote);
        return "/index.xhtml?faces-redirect=true";
    }

    public String delete(int noteid) {
        this.nd.deleteNote(noteid);

        return "/pages/mynotes.xhtml?faces-redirect=true";

    }

    public String updateNotes() {
        this.getNd().updateNote(this.n);
        this.clist = this.getNd().getAllNotes();
        return "/admin/Notes/list.xhtml?faces-redirect=true";
    }

    public String update(Note n) {
        this.n = n;
        return "/admin/Notes/update.xhtml?faces-redirect=true";
    }

    public String openNote(int note_id) {
        this.n = nd.getNote(note_id);
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }

    public Note getNoteById(int note_id) {
        n = getNd().getNote(note_id);
        return n;
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> userList = getNd().getAllNotes();
        return userList;
    }

    public ArrayList<Note> getAllNotesByTag(int tag_id) {
        ArrayList<Note> noteList = new Tag_NoteDao().getAllNotesByTag(tag_id);
        return noteList;
    }

    public ArrayList<Note> getAllPublicNotes() {
        ArrayList<Note> userList = getNd().getAllPublicNotes();
        return userList;
    }

    public ArrayList<Note> getAllUserNotes(int user_id) {
        ArrayList<Note> userList = getNd().getAllUserNotes(user_id);
        return userList;
    }



    public Note getN() {
        if (n == null) {
            n = new Note();
        }
        return n;
    }

    public void setN(Note n) {
        this.n = n;
    }

    public NoteDao getNd() {
        if (nd == null) {
            nd = new NoteDao();
        }
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
    
    public List<String> getTaglist() {
        if(tagsOfNote == null)
            return new ArrayList<String>();
        return tagsOfNote;
    }

    public void setTaglist(List<String> tagsOfNote) {
        this.tagsOfNote = tagsOfNote;
    }

  

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

}
