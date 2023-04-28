package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.orm.api.IManager;
import groupwork.entity.SingerEntity;
import groupwork.exception.ConnectionDataBaseException;
import groupwork.exception.NotFoundDataBaseException;

import javax.persistence.EntityManager;
import java.util.List;

public class SingerDaoDB implements ISingerDao {
    private final IManager manager;

    public SingerDaoDB(IManager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<SingerEntity> resultList = entityManager.createQuery("from SingerEntity", SingerEntity.class).getResultList();
            entityManager.getTransaction().commit();

            return resultList;

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
    public boolean isContain(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            return singerEntity != null;

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
    public void delete(Long id, Long version) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);

            if(singerEntityDB != null && singerEntityDB.getVersion().equals(version)) {
                entityManager.remove(singerEntityDB);
                entityManager.getTransaction().commit();
            } else {
                throw new NotFoundDataBaseException("Delete is not possible. The singer wasn't found in the database");
            }
        } catch (NotFoundDataBaseException e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
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
    public void create(SingerEntity singerEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(singerEntity);
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

    @Override
    public void update(SingerEntity singerEntity) {
        Long id = singerEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);

            if (singerEntityDB != null) {
                entityManager.merge(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                throw new NotFoundDataBaseException("Update is not possible. The singer wasn't found in the database");
            }
        } catch (NotFoundDataBaseException e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
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
    public SingerEntity get(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            return singerEntity;

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
