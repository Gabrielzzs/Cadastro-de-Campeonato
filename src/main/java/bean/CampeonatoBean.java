package bean;

import dao.CampeonatoDAO;
import entidades.Campeonato;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CampeonatoBean {
    private Campeonato campeonato = new Campeonato();
    private CampeonatoDAO campeonatoDAO = new CampeonatoDAO();

    public void salvar() {
        campeonatoDAO.salvar(campeonato);
        campeonato = new Campeonato();
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}
