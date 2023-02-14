package groupwork.dao.db;


import groupwork.dao.api.IGenreDao;
import groupwork.dao.db.orm.api.IManager;
import groupwork.entity.GenreEntity;
import groupwork.exception.ConnectionDataBaseException;
import groupwork.exception.NotFoundDataBaseException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDaoDB implements IGenreDao {
    private IManager manager;

    public GenreDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public List<GenreEntity> getAll() {
        EntityManager entityManager =  null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<GenreEntity> resultList = entityManager.createQuery("from GenreEntity", GenreEntity.class)
                    .getResultList();
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
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            return genreEntity != null;

        } catch (RuntimeException e) {
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(GenreEntity genreEntity) {
        Long id = genreEntity.getId();
        Long version = genreEntity.getVersion();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if(genreEntityDB != null && genreEntityDB.getVersion().equals(version)) {
                entityManager.remove(genreEntityDB);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Delete is not possible. The genre wasn't found in the database");
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
    public void create(GenreEntity genreEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(genreEntity);
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
    public void update(GenreEntity genreEntity) {
        Long id = genreEntity.getId();
        Long version = genreEntity.getVersion();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if (genreEntityDB != null && genreEntityDB.getVersion().equals(version)) {
                entityManager.merge(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Update is not possible. The genre wasn't found in the database");
            }
        } catch (RuntimeException e) {
            if(e instanceof NullPointerException) {
                throw new NotFoundDataBaseException(e.getMessage());
            } else {
                throw new ConnectionDataBaseException("Database connection error", e);
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public GenreEntity get(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            if (genreEntity != null) {

                return genreEntity;

            } else {
                throw new NullPointerException("The genre wasn't found in the database");
            }
        } catch (RuntimeException e) {
            if(e instanceof NullPointerException) {
                throw new NotFoundDataBaseException(e.getMessage());
            } else {
                throw new ConnectionDataBaseException("Database connection error", e);
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}

