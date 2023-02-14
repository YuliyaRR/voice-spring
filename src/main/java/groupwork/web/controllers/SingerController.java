package groupwork.web.controllers;

import groupwork.dto.SingerDTO;
import groupwork.dto.SingerDTOFromDB;
import groupwork.dto.SingerDTOFromDBWithoutVersion;
import groupwork.service.api.ISingerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {
    private final ISingerService singerService;

    public SingerController(ISingerService singerService) {
        this.singerService = singerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SingerDTOFromDBWithoutVersion> getAll() {
        return singerService.get();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SingerDTOFromDB getCard(@PathVariable("id") Long singerID){
        return singerService.get(singerID);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SingerDTO singer) {
        singerService.create(singer);
    }

    @RequestMapping(path = "/{id}/version/{version}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long singerID,
                       @PathVariable("version") Long version,
                       @RequestBody SingerDTO singer) {
        singerService.update(singerID, version, singer);

    }

    @RequestMapping(path = "/{id}/version/{version}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long singerID,
                       @PathVariable("version") Long version) {
        singerService.delete(singerID, version);
    }
}