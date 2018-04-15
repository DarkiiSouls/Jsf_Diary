///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package bean;
//
//import dao.RoleDao;
//import dao.TagDao;
//import dao.Tag_NoteDao;
//import entity.Role;
//import entity.Tag;
//import entity.User;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
///**
// *
// * @author erkan
// */
//@ManagedBean
//@SessionScoped
//public class RoleBean implements Serializable {
//    private Role role;
//    private RoleDao roleDao;
//    private List<Role> rolelist = null;
//    private User u;
//    
//     public String createRole(){
//        getRoleDao().saveRole(getRole());
//        setRole(new Role());
//        return null;
//    }
//    public String createRoleForm(User user) {
//        if(user.getId() != 1){
//            return "/login.xhtml?faces-redirect=true";
//        }
//       Role=getRole();
//       getRoleDao().saveRole(getRole());
//       setRole(new Role());
//     
//        return "/admin/Role/list.xhtml?faces-redirect=true";
//    }
//    
//    public String delete(int role_id) {
//        this.getRoleDao().deleteRole(role_id);
//       
//        return null;
//    }
//        public String updateRole(){
//        this.getRoleDao().updateRole(this.role);
//        this.rolelist=this.getRoleDao().getAllRole();
//        
//        return "/admin/Role/list.xhtml?faces-redirect=true";
//    }
//    public String update(Role t){
//        this.role=t;
//        return "/admin/Role/update.xhtml?faces-redirect=true";
//    }
//    public ArrayList<Role> getAllRole (){
//        ArrayList<Role> roleList = getRoleDao().getAllRole();
//        return rolelist;
//    }
//    
//    public ArrayList<Role> getAllRoleByNote (int note_id){
//        ArrayList<Tag> tagList = new Role_NoteDao().getAllRolesByNote(note_id);
//        return rolelist;
//    }
//
//    public Role getRole() {
//        if (role == null) {
//            role = new Role();
//        }
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public RoleDao getRoleDao() {
//        if (roleDao == null) {
//            roleDao = new RoleDao();
//        }
//        return roleDao;
//    }
//
//    public void setRoleDao(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }
//
//    public List<Role> getRolelist() {
//        return rolelist;
//    }
//
//    public void setRolelist(List<Role> rolelist) {
//        this.rolelist = rolelist;
//    }
//    
//     public String openrole(int role_id) {
//        this.role= roleDao.getRole(role_id);
//        return "/admin/Role/detail.xhtml?faces-redirect=true";
//    }
//    
//}
