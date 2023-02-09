package groupwork.service.api;

import groupwork.entity.SavedVoice;


public interface IMailService {
    void send(SavedVoice savedVoiceEntity, long id);
}
