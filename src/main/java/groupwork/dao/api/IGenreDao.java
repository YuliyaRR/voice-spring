package groupwork.dao.api;

import groupwork.entity.GenreEntity;

import java.util.List;

public interface IGenreDao {

    List<GenreEntity> getAll();

    boolean isContain(Long id);

    void delete(Long id, Long version);

    void create(GenreEntity genreEntity);

    void update(GenreEntity genreEntity);
    GenreEntity get(Long id);
}
