package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.db.orm.api.IManager;
import groupwork.entity.SingerEntity;
import groupwork.exception.ConnectionDataBaseException;
import groupwork.exception.NotFoundDataBaseException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SingerDaoDB implements ISingerDao {
    private final IManager manager;

    public SingerDaoDB(IManager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getSingerList() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<SingerEntity> resultList = entityManager.createQuery("from SingerEntity", SingerEntity.class).getResultList();
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
    public boolean isContain(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            return singerEntity != null;

        } catch (RuntimeException e) {
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(SingerEntity singerEntity) {
        Long id = singerEntity.getId();
        Long version = singerEntity.getVersion();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);
            if(singerEntityDB != null && singerEntityDB.getVersion().equals(version)) {
                entityManager.remove(singerEntityDB);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Delete is not possible. The singer wasn't found in the database");
            }
        } catch (RuntimeException e) {
            if(e instanceof NullPointerException) {
                throw new NotFoundDataBaseException(e.getMessage());
            } else {
                throw new ConnectionDataBaseException("Database connection error", e);
            }
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
        } catch (RuntimeException e) {
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
        Long version = singerEntity.getVersion();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);

            if (singerEntityDB != null && singerEntityDB.getVersion().equals(version)) {
                entityManager.merge(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Update is not possible. The singer wasn't found in the database");
            }
        } catch (RuntimeException e) {
            if(e instanceof NullPointerException) {
                throw new NotFoundDataBaseException(e.getMessage());
            } else {
                throw new ConnectionDataBaseException("Database connection error", e);
            }
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

            if(singerEntity != null){

                return singerEntity;

            } else {
                throw new NotFoundDataBaseException("The singer wasn't found in the database");
            }

        } catch (RuntimeException e) {
            if(e instanceof NullPointerException) {
                throw new NotFoundDataBaseException(e.getMessage());
            } else {
                throw new ConnectionDataBaseException("Database connection error", e);
            }

        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }
}
