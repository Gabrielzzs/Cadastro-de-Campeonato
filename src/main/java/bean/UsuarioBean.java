package bean;

import dao.UsuarioDAO;
import entidades.Usuario;
import util.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




import java.util.List;

@ManagedBean
@ViewScoped
public class UsuarioBean {
    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuarios;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Usuario> getListaUsuarios() {
        if (listaUsuarios == null) {
            listaUsuarios = usuarioDAO.listar();
        }
        return listaUsuarios;
    }

    public void salvar() {
        try {
            usuarioDAO.salvar(usuario); 
            MessageUtil.addInfoMsg("Sucesso", "Usuário salvo com sucesso");
            usuario = new Usuario(); 
        } catch (Exception e) {
        	MessageUtil.addErrorMsg("Erro", "Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
