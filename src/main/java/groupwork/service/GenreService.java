package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.dto.GenreDTOFull;
import groupwork.dto.GenreDTOBrief;
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
    public List<GenreDTOBrief> get() {
        List<GenreEntity> genreList = dao.getAll();
        List<GenreDTOBrief> list = new ArrayList<>();

        for (GenreEntity genreEntity : genreList) {
            list.add(new GenreDTOBrief(genreEntity.getName(), genreEntity.getId()));
        }

        return list;
    }

    @Override
    public void delete(Long id, Long version) {
        GenreEntity genreEntityDB = dao.get(id);
        if (genreEntityDB != null) {
            if (genreEntityDB.getVersion().equals(version)) {
                dao.delete(genreEntityDB);
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
            throw new InvalidInputServiceException("Genre name isn't specified");
        }
    }

    @Override
    public void update(Long id, Long version, GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if (genre == null || genre.isBlank()) {
            throw new InvalidInputServiceException("Genre name isn't specified");
        }

        GenreEntity entityDB = dao.get(id);

        if(entityDB != null) {
            if(entityDB.getVersion().equals(version)) {
                entityDB.setName(genre);
                dao.update(entityDB);
            } else {
                throw new InvalidInputServiceException("This version genre was changed or was deleted yet");
            }
        } else {
            throw new InvalidInputServiceException("Genre with this id was not found in the database");
        }
    }

    @Override
    public GenreDTOFull get(Long id) {
        GenreEntity genreEntity = this.dao.get(id);
        if(genreEntity != null) {
            return new GenreDTOFull(genreEntity.getName(), genreEntity.getId(), genreEntity.getVersion());
        } else {
            throw new InvalidInputServiceException("Genre with this id was not found in the database");
        }
    }
}
