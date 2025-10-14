package dao;

import entidades.Campeonato;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CampeonatoDAO {

    public void salvar(Campeonato campeonato) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(campeonato);
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

    public List<Campeonato> listar() {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.createQuery("FROM Campeonato", Campeonato.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Campeonato buscarPorId(Integer id) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.find(Campeonato.class, id); 
        } finally {
            em.close();
        }
    }

}
