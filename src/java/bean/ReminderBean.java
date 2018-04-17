/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ReminderDao;
import entity.Reminder;
import entity.User;
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
public class ReminderBean {
    private Reminder rmd;
    private ReminderDao rmdDao;
    private List<Reminder> rmdList=null;
    private User u;

     public String createRemander (){
        getRmdDao().saveRemander(getRmd());
        setRmd(new Reminder());
        return null;
    }
     public String createRemanderForm (User user){
        Reminder rmd=getRmd();
        rmd.setUser(user);
        getRmdDao().saveRemander(getRmd());
        setRmd(new Reminder());
        return "/admin/Reminder/list.xhtml?faces-redirect=true";
    }
      public String delete(int rmd_id) {
        this.rmdDao.deleteRemander(rmd_id);
         
             return null;  
    }
           public String updateRemander(){
               this.getRmdDao().updateRemander(this.rmd);
               this.rmdList=this.getRmdDao().getRemander();
        return "/admin/Reminder/list.xhtml?faces-redirect=true";
    }
    public String update(Reminder rmd){
        this.rmd=rmd;
        return "/admin/Reminder/update.xhtml?faces-redirect=true";
    }
       public ArrayList<Reminder> getAllReminder (){
        ArrayList<Reminder> rmdList = getRmdDao().getRemander();
        return rmdList;
    }
    public Reminder getRmd() {
        if (rmd == null) {
            rmd = new Reminder();
        }
        return rmd;
    }

    public void setRmd(Reminder rmd) {
        this.rmd = rmd;
    }

    public ReminderDao getRmdDao() {
        if (rmdDao == null) {
            rmdDao = new ReminderDao();
        }
        return rmdDao;
    }

    public void setRmdDao(ReminderDao rmdDao) {
        this.rmdDao = rmdDao;
    }

    public List<Reminder> getRmdList() {
        return rmdList;
    }

    public void setRmdList(List<Reminder> rmdList) {
        this.rmdList = rmdList;
    }

  
    
}
