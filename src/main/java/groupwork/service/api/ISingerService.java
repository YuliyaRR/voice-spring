package groupwork.service.api;

import groupwork.dto.SingerDTO;
import groupwork.dto.SingerDTOFull;
import groupwork.dto.SingerDTOBrief;

import java.util.List;

public interface ISingerService {

    boolean isContain(Long id);

    List<SingerDTOBrief> get();

    void delete(Long id, Long version);

    void create(SingerDTO singerDTO);

    void update(Long id, Long version, SingerDTO singerDTO);

    SingerDTOFull get(Long id);
}
