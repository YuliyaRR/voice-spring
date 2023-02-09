package groupwork.dao.db;


import groupwork.dao.api.IGenreDao;
import groupwork.dao.db.orm.api.IManager;
import groupwork.entity.GenreEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDaoDB implements IGenreDao {
    private IManager manager;

    public GenreDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public List<GenreEntity> getGenreList() {
        EntityManager entityManager = null;
        List<GenreEntity> resultList;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            resultList = entityManager.createQuery("from GenreEntity", GenreEntity.class).getResultList();
            entityManager.getTransaction().commit();

        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
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
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            if (genreEntity != null) {
                result = true;
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    @Override
    public void delete(GenreEntity genreEntity) {
        long id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            genreEntity = entityManager.find(GenreEntity.class, id);
            if (genreEntity != null) {
                entityManager.remove(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().commit();
                throw new NullPointerException("Delete is not possible. The singer wasn't found in the database");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void create(GenreEntity genreEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(genreEntity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(GenreEntity genreEntity) {
        long id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if (genreEntityDB != null) {
                entityManager.merge(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().commit();
                throw new NullPointerException("Update is not possible. The genre wasn't found in the database");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public GenreEntity get(long id) {
        EntityManager entityManager = null;
        GenreEntity genreEntity;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            if (genreEntity == null) {
                throw new NullPointerException("The genre wasn't found in the database");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("DataBase error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return genreEntity;
    }
}

