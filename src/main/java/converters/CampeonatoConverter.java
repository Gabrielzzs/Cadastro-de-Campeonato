package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import dao.CampeonatoDAO;
import entidades.Campeonato;

@FacesConverter(value = "campeonatoConverter")
public class CampeonatoConverter implements Converter {

    private CampeonatoDAO campeonatoDAO = new CampeonatoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.valueOf(value);
            return campeonatoDAO.buscarPorId(id); 
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Campeonato)) {
            return "";
        }
        Campeonato campeonato = (Campeonato) value;
        return String.valueOf(campeonato.getId()); 
    }
}
