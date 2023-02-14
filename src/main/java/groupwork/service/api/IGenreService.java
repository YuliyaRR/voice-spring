package groupwork.service.api;

import groupwork.dto.GenreDTO;
import groupwork.dto.GenreDTOFromDB;
import groupwork.dto.GenreDTOFromDBWithoutVersion;

import java.util.List;

public interface IGenreService {

    boolean isContain(Long id);

    List<GenreDTOFromDBWithoutVersion> get();

    void delete(Long id, Long version);

    void create(GenreDTO genreDTO);

    void update(Long id, Long version, GenreDTO genreDTO);

    GenreDTOFromDB get(Long id);

}
