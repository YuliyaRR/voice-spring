package groupwork.dao.db;

import groupwork.dao.api.IVotingDao;
import groupwork.dao.orm.api.IManager;
import groupwork.entity.VoiceEntity;
import groupwork.exception.ConnectionDataBaseException;

import javax.persistence.EntityManager;
import java.util.List;


public class VotingDaoDB implements IVotingDao {
    private final IManager manager;

    public VotingDaoDB(IManager manager) {
        this.manager = manager;
    }


    @Override
    public List<VoiceEntity> getVoices() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<VoiceEntity> result = entityManager.createQuery("from VoiceEntity", VoiceEntity.class)
                    .getResultList();
            entityManager.getTransaction().commit();

            return result;

        } catch (Exception e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }

    }

    @Override
    public void save(VoiceEntity voice) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(voice);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }
}
