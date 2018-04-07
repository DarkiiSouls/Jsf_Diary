
package bean;

import dao.UserDao;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 7765876811740798583L;
    UserDao userDao;
    User loggeduser;
    boolean girisYaptiMi;    
    boolean isAdmin;
    private ArrayList<User> userlist;

    
    public String giriYap(){
        loggeduser=getUserDao().getUserLogin(loggeduser.getUsername(),loggeduser.getPassword());
        if (loggeduser!=null){
            girisYaptiMi=true;
            if (loggeduser.getRole()==1)
                isAdmin = true;
            else
                isAdmin = false;
            return "/index.xhtml?faces-redirect=true";
        }
        else{
            girisYaptiMi=false;
            isAdmin = false;
            loggeduser=null;
        }
        return null;
    }
    
    public String cikisYap(){
        girisYaptiMi=false;
        isAdmin = false;
        loggeduser=null;
        return "/index.xhtml";
    }

    public UserDao getUserDao() {
        if (userDao==null)
            userDao= new UserDao();
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getLoggeduser() {
        if (loggeduser==null)
            loggeduser= new User();
        return loggeduser;
    }
    
    public int getLoggedUserID(){
        if (loggeduser==null)
            return 0;
        return loggeduser.getId();
    }

    public void setLoggeduser(User loggeduser) {
        this.loggeduser = loggeduser;
    }

    public boolean isGirisYaptiMi() {
        return girisYaptiMi;
    }

    public void setGirisYaptiMi(boolean girisYaptiMi) {
        this.girisYaptiMi = girisYaptiMi;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
