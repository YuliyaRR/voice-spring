package groupwork.dao.db;

import groupwork.dao.api.IVotingDao;
import groupwork.dao.db.orm.api.IManager;
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
    public List<VoiceEntity> getVoiceList() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<VoiceEntity> resultList = entityManager.createQuery("from VoiceEntity", VoiceEntity.class).getResultList();
            entityManager.getTransaction().commit();

            return resultList;

        } catch (RuntimeException e) {
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
        } catch (RuntimeException e) {
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }
}
