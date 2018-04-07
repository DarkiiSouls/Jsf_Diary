/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.UserDao;
import entity.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("UserConverter")
public class UserConverter implements Converter {

    private final UserDao ud = new UserDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            User user = ud.getUser(Integer.parseInt(value));
            return user;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof User) {
            String tmp = String.valueOf(((User) value).getId());
            return tmp;
        }
        return null;
    }


}
