package bean;

import dao.UsuarioDAO;
import entidades.Usuario;
import util.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class LoginBean {
    private String login;
    private String senha;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String login() {
        for (Usuario usuario : usuarioDAO.listar()) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                return "opcoes?faces-redirect=true";
            }
        }
        MessageUtil.addErrorMsg("Erro", "Login ou senha inválidos.");
        return null;
    }
}
