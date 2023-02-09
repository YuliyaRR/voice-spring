package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.db.orm.api.IManager;
import groupwork.entity.SingerEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class SingerDaoDB implements ISingerDao {
    private final IManager manager;

    public SingerDaoDB(IManager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getSingerList() {
        EntityManager entityManager = null;
        List<SingerEntity> resultList;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            resultList = entityManager.createQuery("from SingerEntity", SingerEntity.class).getResultList();
            entityManager.getTransaction().commit();

        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return resultList;
    }

    @Override
    public boolean isContain(long id) {
        boolean result = false;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            if(singerEntity != null) {
                result = true;
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    @Override
    public void delete(SingerEntity singerEntity) {
        long id = singerEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            singerEntity = entityManager.find(SingerEntity.class, id);
            if(singerEntity != null) {
                entityManager.remove(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().commit();
                throw new NullPointerException("Delete is not possible. The singer wasn't found in the database");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
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
            throw new RuntimeException("DataBase error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(SingerEntity singerEntity) {
        long id = singerEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);

            if(singerEntityDB != null) {
                entityManager.merge(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().commit();
                throw new NullPointerException("Update is not possible. The singer wasn't found in the database");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }
    @Override
    public SingerEntity get(long id) {
        EntityManager entityManager = null;
        SingerEntity singerEntity;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            if(singerEntity == null){
                throw new NullPointerException("The singer wasn't found in the database");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка БД", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return singerEntity;
    }
}
