package bean;

import javax.faces.bean.ManagedBean;
import java.util.List;
import dao.JogoDAO;
import entidades.Jogo;

@ManagedBean
public class FiltroBean {
    private String timeSelecionado;
    private List<Jogo> jogosFiltrados;

    private JogoDAO jogoDAO = new JogoDAO();

    public void buscarJogos() {
        jogosFiltrados = jogoDAO.buscarPorTime(timeSelecionado);
    }

    public String getTimeSelecionado() {
        return timeSelecionado;
    }

    public void setTimeSelecionado(String timeSelecionado) {
        this.timeSelecionado = timeSelecionado;
    }

    public List<Jogo> getJogosFiltrados() {
        return jogosFiltrados;
    }

    public void setJogosFiltrados(List<Jogo> jogosFiltrados) {
        this.jogosFiltrados = jogosFiltrados;
    }
}

