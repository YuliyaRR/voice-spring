package groupwork.dao.db;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.orm.api.IManager;
import groupwork.entity.GenreEntity;
import groupwork.exception.ConnectionDataBaseException;
import groupwork.exception.NotFoundDataBaseException;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDaoDB implements IGenreDao {
    private final IManager manager;

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
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            return genreEntity != null;

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
    public void delete(GenreEntity genreEntity) {
        Long id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if (genreEntityDB != null) {
                entityManager.remove(genreEntityDB);
                entityManager.getTransaction().commit();
            } else {
                if(entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                throw new NotFoundDataBaseException("Delete is not possible. The genre wasn't found in the database");
            }
        } catch (NotFoundDataBaseException e) {
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
    public void create(GenreEntity genreEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(genreEntity);
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
    public void update(GenreEntity genreEntity) {
        Long id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if (genreEntityDB != null) {
                entityManager.merge(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                if(entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                throw new NotFoundDataBaseException("Update is not possible. The genre wasn't found in the database");
            }
        } catch (NotFoundDataBaseException e) {
            throw e;
        } catch (Exception e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ConnectionDataBaseException("Database connection error", e);
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

            return genreEntity;

        } catch (Exception e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ConnectionDataBaseException("Database connection error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}

