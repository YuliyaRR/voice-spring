package groupwork.dao.api;

import groupwork.entity.VoiceEntity;

import java.util.List;

public interface IVotingDao {
    List<VoiceEntity> getVoiceList();

    void save(VoiceEntity voiceEntity);
}
