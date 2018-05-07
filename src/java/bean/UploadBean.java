/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Utilty.DBManager;
import dao.PhotoDao;
import entity.Photo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author erkan
 */
@ManagedBean
@SessionScoped
public class UploadBean implements Serializable {

    private Part part;

    private List<Photo> photolist;
    private PhotoDao pd;

    private String uploadPath = "C:\\upload\\";

    public void upload() throws IOException {
        try {

            InputStream input = part.getInputStream();
            File f = new File(this.uploadPath + part.getSubmittedFileName());
            Files.copy(input, f.toPath(), REPLACE_EXISTING);

            Photo pt = new Photo();

            pt.setUrl(f.getParent());

            pt.setName(f.getName());
            
            pt.setType(part.getContentType());
            
            
            this.getPd().insert(pt);
            
           

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Photo> getAllList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        List<Photo> fileList = getPd().getList();
        return fileList;
       
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public PhotoDao getPd() {
        if (pd == null) {
            pd = new PhotoDao();
        }

        return pd;
    }

    public void setPd(PhotoDao pd) {
        this.pd = pd;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public List<Photo> getPhotolist() {
        this.photolist = getPd().getList();
        return photolist;
    }

    public void setPhotolist(List<Photo> photolist) {
        this.photolist = photolist;
    }

}
/*      String format = "dd -MM -yyyy -hhmmss";
            SimpleDateFormat sdf= new SimpleDateFormat(format);
            String extension = f.getName().substring(f.getName().lastIndexOf("."));
            String fileName = sdf.format("dd -MM -yyyy -hhmmss") + extension ;
            File newFile= new File(this.uploadPath+fileName);
            f.renameTo(newFile);
 */
