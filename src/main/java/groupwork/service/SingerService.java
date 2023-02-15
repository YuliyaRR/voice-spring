package groupwork.service;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;
import groupwork.dto.SingerDTOFull;
import groupwork.dto.SingerDTOBrief;
import groupwork.entity.SingerEntity;
import groupwork.exception.InvalidInputServiceException;
import groupwork.service.api.ISingerService;

import java.util.ArrayList;
import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao dao;

    public SingerService(ISingerDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean isContain(Long id) {
        return this.dao.isContain(id);
    }

    @Override
    public List<SingerDTOBrief> get() {
        List<SingerEntity> singerList = dao.getAll();
        List<SingerDTOBrief>list = new ArrayList<>();

        for (SingerEntity singerEntity : singerList) {
            list.add(new SingerDTOBrief(singerEntity.getName(), singerEntity.getId()));
        }

        return list;
    }

    @Override
    public void delete(Long id, Long version) {
        SingerEntity singerEntityDB = dao.get(id);

        if(singerEntityDB != null) {
            if(singerEntityDB.getVersion().equals(version)) {
                dao.delete(new SingerEntity(id, version));
            } else {
                throw new InvalidInputServiceException("Singer's version is invalid");
            }
        } else {
            throw new InvalidInputServiceException("Singer with this id was not found in the database");
        }
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String name = singerDTO.getName();
        if (name != null && !name.isBlank()) {
            dao.create(new SingerEntity(name));
        } else {
            throw new InvalidInputServiceException("Singer name not specified");
        }
    }

    @Override
    public void update(Long id, Long version, SingerDTO singerDTO) {
        String singer = singerDTO.getName();

        if (singer == null || singer.isBlank()) {
            throw new InvalidInputServiceException("Singer name not specified");
        }

        SingerEntity singerEntityDB = dao.get(id);

        if(singerEntityDB != null) {
            if(singerEntityDB.getVersion().equals(version)) {
                singerEntityDB.setName(singer);
                dao.update(singerEntityDB);
            } else {
                throw new InvalidInputServiceException("Singer's version is invalid");
            }
        } else {
            throw new InvalidInputServiceException("Singer with this id was not found in the database");
        }
    }

    @Override
    public SingerDTOFull get(Long id) {
        SingerEntity singerEntity = this.dao.get(id);
        if(singerEntity != null) {
            return new SingerDTOFull(singerEntity.getName(), singerEntity.getId(), singerEntity.getVersion());
        } else {
            throw new InvalidInputServiceException("Singer with this id was not found in the database");
        }
    }
}
