package groupwork.web.controllers;

import groupwork.dto.GenreDTO;
import groupwork.dto.GenreDTOFull;
import groupwork.dto.GenreDTOBrief;
import groupwork.service.api.IGenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final IGenreService genreService;

    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GenreDTOBrief> getAll() {
        return genreService.get();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GenreDTOFull getCard(@PathVariable("id") Long genreID) {
        return genreService.get(genreID);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GenreDTO genre) {
        genreService.create(genre);
    }

    @RequestMapping(path = "/{id}/version/{version}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long genreID,
                       @PathVariable("version") Long version,
                       @RequestBody GenreDTO genre) {
        genreService.update(genreID, version, genre);
    }

    @RequestMapping(path = "/{id}/version/{version}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long genreID,
                       @PathVariable("version") Long version) {
        genreService.delete(genreID, version);
    }
}
