package groupwork.dao.api;

import groupwork.entity.SingerEntity;

import java.util.List;

public interface ISingerDao {
    List<SingerEntity> getAll();

    boolean isContain(Long id);

    void delete(Long id, Long version);

    void create(SingerEntity singerEntity);

    void update(SingerEntity singerEntity);
    SingerEntity get(Long id);
}
