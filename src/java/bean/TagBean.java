/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.TagDao;
import dao.Tag_NoteDao;
import entity.Tag;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author erkan
 */
@ManagedBean
@SessionScoped
public class TagBean implements Serializable {
    private Tag tag;
    private TagDao tagDao;
    private List<Tag> taglist = null;
    private User u;
    
     public String createTag(){
        getTagDao().saveTag(getTag());
        setTag(new Tag());
        return null;
    }
    public String createTagForm(User user) {
        if(user.getId() != 1){
            return "/login.xhtml?faces-redirect=true";
        }
       Tag t=getTag();
       getTagDao().saveTag(getTag());
       setTag(new Tag());
     
        return "/admin/Tags/list.xhtml?faces-redirect=true";
    }
    
    public String delete(int tag_id) {
        this.getTagDao().deleteTag(tag_id);
       
        return null;
    }
        public String updateTags(){
        this.getTagDao().updateTag(this.tag);
        this.taglist=this.getTagDao().getAllTags();
        
        return "/admin/Tags/list.xhtml?faces-redirect=true";
    }
    public String update(Tag t){
        this.tag=t;
        return "/admin/Tags/update.xhtml?faces-redirect=true";
    }
    public ArrayList<Tag> getAllTags (){
        ArrayList<Tag> tagList = getTagDao().getAllTags();
        return tagList;
    }
    
    public ArrayList<Tag> getAllTagsByNote (int note_id){
        ArrayList<Tag> tagList = new Tag_NoteDao().getAllTagsByNote(note_id);
        return tagList;
    }

    public Tag getTag() {
        if (tag == null) {
            tag = new Tag();
        }
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public TagDao getTagDao() {
        if (tagDao == null) {
            tagDao = new TagDao();
        }
        return tagDao;
    }

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> getTaglist() {
        return taglist;
    }

    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }
    
     public String openTag(int tag_id) {
        this.tag = tagDao.getTag(tag_id);
        return "/admin/Tags/detail.xhtml?faces-redirect=true";
    }
     public String openTagMain(int tag_id) {
        this.tag = tagDao.getTag(tag_id);
        return "/pages/tags.xhtml?faces-redirect=true";
    }
    
}
