package ru.terra.tboard.db.controller;

import ru.terra.server.db.controllers.AbstractJpaController;
import ru.terra.tboard.db.entity.Post;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Date: 27.02.14
 * Time: 15:56
 */
public class PostJpaController extends AbstractJpaController<Post> {
    public PostJpaController() {
        super(Post.class);
    }

    @Override
    public void create(Post post) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(post);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(Integer integer) throws Exception {

    }

    @Override
    public void update(Post post) throws Exception {

    }

    public List<Post> getBoard(String board, Boolean all, Integer page, Integer perpage) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Post.getBoard");
            q.setParameter("board", board);
            if (!all) {
                q.setMaxResults(perpage);
                q.setFirstResult(page * perpage);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Post> getThread(String board, Integer thread) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Post.getThread");
            q.setParameter("board", board);
            q.setParameter("parent", thread);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
