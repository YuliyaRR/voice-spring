package groupwork.service.api;

import groupwork.dto.GenreDTO;
import groupwork.dto.GenreDTOFull;
import groupwork.dto.GenreDTOBrief;

import java.util.List;

public interface IGenreService {

    boolean isContain(Long id);

    List<GenreDTOBrief> get();

    void delete(Long id, Long version);

    void create(GenreDTO genreDTO);

    void update(Long id, Long version, GenreDTO genreDTO);

    GenreDTOFull get(Long id);

}
