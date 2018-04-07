package bean;

import dao.UserDao;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class UserBean implements Serializable{
    private static final long serialVersionUID = 7765876811740798583L;
    private User user;
    private UserDao userDao;
    private List<User> clist = null;
    
    
    
    public String createUser (){
        getUserDao().saveUser(getUser());
        this.user= null;
        return "/admin/User/list.xhtml?faces-redirect=true";
    }
    public String create() {
        this.user = null;
        return "/admin/User/create.xhtml?faces-redirect=true";
    }
     public String delete(int Userid) {
        this.getUserDao().deleteUser(Userid);
        return "/admin/User/list.xhtml?faces-redirect=true";
    }
        public String updateUsers(){
        this.getUserDao().updateUser(this.user);
        this.clist=this.getUserDao().getAllUsers();
        return "/admin/User/list.xhtml?faces-redirect=true";
    }
    public String update(User u){
        this.user=u;
        return "/admin/User/update.xhtml?faces-redirect=true";
    }
    
    public String getUserById (int user_id){
        this.user=getUserDao().getUser(user_id);
        return null;
    }
    
    public ArrayList<User> getAllUsers (){
        ArrayList<User> userList = getUserDao().getAllUsers();
        return userList;
    }
     public String openNote(int user_id){
        this.user = userDao.getUser(user_id);
        return "/pages/notedetails.xhtml?faces-redirect=true";
    }

    public User getUser() {
        if (this.user==null)
            this.user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDao getUserDao() {
        if (this.userDao==null)
            this.userDao = new UserDao();
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    
    
}
