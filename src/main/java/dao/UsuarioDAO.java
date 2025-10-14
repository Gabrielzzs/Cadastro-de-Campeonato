package dao;

import entidades.Usuario;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO {
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Usuario> listar() {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }
}
