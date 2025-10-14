package bean;

import dao.JogoDAO;
import dao.CampeonatoDAO;
import entidades.Jogo;
import entidades.Usuario;
import util.MessageUtil;
import entidades.Campeonato;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.RowEditEvent;

import java.util.Date;
import java.util.List;


@ManagedBean
public class JogoBean {
    private Jogo jogo = new Jogo();
    private JogoDAO jogoDAO = new JogoDAO();
    private CampeonatoDAO campeonatoDAO = new CampeonatoDAO();
    private List<Jogo> listaJogos; 


    public void salvar() {
        try {
            if (jogo.getTime1().equals(jogo.getTime2())) {
                throw new IllegalArgumentException("Os times não podem ser iguais!");
            }
            jogo.setDataCadastro(new Date()); 
            jogoDAO.salvar(jogo); 
            MessageUtil.addInfoMsg("Sucesso", "Jogo cadastrado com sucesso!");
            limparFormulario(); 
        } catch (IllegalArgumentException e) {
            MessageUtil.addErrorMsg("Erro", e.getMessage());
        } catch (Exception e) {
            MessageUtil.addErrorMsg("Erro", "Não foi possível salvar o jogo: " + e.getMessage());
        }
    }
    
    private void limparFormulario() {
        jogo = new Jogo(); // 
    }

    public List<Campeonato> getListaCampeonatos() {
        return campeonatoDAO.listar(); 
    }

    public void excluir(Jogo jogo) {
        try {
            jogoDAO.excluir(jogo.getId());
            listaJogos.remove(jogo);
            MessageUtil.addInfoMsg("Sucesso", "Jogo excluído com sucesso!");
        } catch (Exception e) {
            MessageUtil.addErrorMsg("Erro", "Erro ao excluir o jogo: " + e.getMessage());
        }
    }
    
    public List<Jogo> getListaJogos() {
        if (listaJogos == null) { 
            listaJogos = jogoDAO.listar();
        }
        return listaJogos;
    }

    public void atualizarListaJogos() {
        listaJogos = jogoDAO.listar(); 
    }


    public void onRowEdit(RowEditEvent<Jogo> event) {       
        try {
        	Jogo jogoEditado = event.getObject();
            jogoDAO.editar(jogoEditado);
            MessageUtil.addInfoMsg("Sucesso", "Jogo editado com sucesso!");
        } catch (Exception e) {
            MessageUtil.addErrorMsg("Erro", "Erro ao editar o jogo: " + e.getMessage());
        }
    }

    
    public void onRowCancel(RowEditEvent<Usuario> event) {
    	MessageUtil.addInfoMsg("Cancelado", "Edição cancelada");
    }


    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}

