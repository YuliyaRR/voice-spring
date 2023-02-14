package groupwork.service.api;

import groupwork.dto.SingerDTO;
import groupwork.dto.SingerDTOFromDB;
import groupwork.dto.SingerDTOFromDBWithoutVersion;

import java.util.List;

public interface ISingerService {

    boolean isContain(Long id);

    List<SingerDTOFromDBWithoutVersion> get();

    void delete(Long id, Long version);

    void create(SingerDTO singerDTO);

    void update(Long id, Long version, SingerDTO singerDTO);

    SingerDTOFromDB get(Long id);
}
