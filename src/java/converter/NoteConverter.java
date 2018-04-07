/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.NoteDao;
import entity.Note;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("NoteConverter")
public class NoteConverter implements Converter {

    private final NoteDao nd = new NoteDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            Note note = nd.getNote(Integer.parseInt(value));
            return note;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Note) {
            String tmp = String.valueOf(((Note) value).getId());
            return tmp;
        }
        return null;
    }


}
