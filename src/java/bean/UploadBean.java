/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
public class UploadBean {
     private Part image;
    private boolean upladed;
    
    public void doUpload(){
        try{
            InputStream in=image.getInputStream();
          //  C:\Users\erkan\Documents\NetBeansProjects\internet\web\resources\image
            File f=new File("C:\\Users\\erkan\\OneDrive\\Masaüstü\\diary\\web\\resources\\resim\\"+image.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out=new FileOutputStream(f);
            
            byte[] buffer=new byte[1024];
            int length;
            while((length=in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
            upladed=true;
            
        }catch(Exception e)
        {
            e.printStackTrace(System.out);
       }
}

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }
}
