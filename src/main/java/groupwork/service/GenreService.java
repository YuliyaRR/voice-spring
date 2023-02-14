package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.dto.GenreDTOFromDB;
import groupwork.dto.GenreDTOFromDBWithoutVersion;
import groupwork.entity.GenreEntity;
import groupwork.exception.InvalidInputServiceException;
import groupwork.service.api.IGenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean isContain(Long id) {
        return this.dao.isContain(id);
    }

    @Override
    public List<GenreDTOFromDBWithoutVersion> get() {
        List<GenreEntity> genreList = dao.getAll();
        List<GenreDTOFromDBWithoutVersion> list = new ArrayList<>();

        for (GenreEntity genreEntity : genreList) {
            list.add(new GenreDTOFromDBWithoutVersion(genreEntity.getName(), genreEntity.getId()));
        }

        return list;
    }

    @Override
    public void delete(Long id, Long version) {
        GenreEntity genreEntityDB = dao.get(id);
        if (genreEntityDB != null) {
            if (genreEntityDB.getVersion().equals(version)){
                dao.delete(new GenreEntity(id, version));
            } else {
                throw new InvalidInputServiceException("This version genre was changed or was deleted yet");
            }
        } else {
            throw new InvalidInputServiceException("Genre with this id was not found in the database");
        }
    }

    @Override
    public void create(GenreDTO genreDTO) {
        String name = genreDTO.getName();
        if (name != null && !name.isBlank()) {
            dao.create(new GenreEntity(name));
        } else {
            throw new InvalidInputServiceException("Genre name not specified");
        }
    }

    @Override
    public void update(Long id, Long version, GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if (genre == null || genre.isBlank()) {
            throw new InvalidInputServiceException("Genre name not specified");
        }

        GenreEntity entityDB = dao.get(id);
        if(entityDB != null) {
            if(entityDB.getVersion().equals(version)) {
                GenreEntity genreEntity = new GenreEntity(id, version, genre);
                dao.update(genreEntity);
            } else {
                throw new InvalidInputServiceException("This version genre was changed or was deleted yet");
            }
        } else {
            throw new InvalidInputServiceException("Genre with this id was not found in the database");
        }
    }

    @Override
    public GenreDTOFromDB get(Long id) {
        GenreEntity genreEntity = this.dao.get(id);
        if(genreEntity != null) {
            return new GenreDTOFromDB(genreEntity.getName(), genreEntity.getId(), genreEntity.getVersion());
        } else {
            throw new InvalidInputServiceException("Genre with this id was not found in the database");
        }
    }
}
