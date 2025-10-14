package dao;

import entidades.Jogo;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class JogoDAO {

    public void salvar(Jogo jogo) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jogo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void editar(Jogo jogo) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jogo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluir(Integer id) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            Jogo jogo = em.find(Jogo.class, id);
            if (jogo != null) {
                em.remove(jogo);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Jogo> listar() {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.createQuery("FROM Jogo", Jogo.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Jogo> buscarPorTime(String time) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.createNamedQuery("Jogo.buscarPorTime", Jogo.class)
                     .setParameter("time", time)
                     .getResultList();
        } finally {
            em.close();
        }
    }


}
