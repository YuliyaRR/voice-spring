package groupwork.service;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.*;
import groupwork.entity.GenreEntity;
import groupwork.entity.SingerEntity;
import groupwork.entity.VoiceEntity;
import groupwork.exception.InvalidInputServiceException;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IVotesService;

import java.util.*;


public class VoteService implements IVotesService {
    private final IVotingDao votingDao;

    private final ISingerService singerService;

    private final IGenreService genreService;


    public VoteService(IVotingDao voiceDao, ISingerService singerService, IGenreService genreService){
        this.votingDao = voiceDao;
        this.singerService = singerService;
        this.genreService = genreService;
    }


    @Override
    public void save(VoiceDTO voice) {
        check(voice);

        Long singer = voice.getSinger();
        Long[] genres = voice.getGenre();
        String message = voice.getMessage();

        SingerDTOFull singerDTOFull = singerService.get(singer);
        SingerEntity singerEntity = new SingerEntity(singer, singerDTOFull.getVersion());

        List<GenreEntity>listGenre = new ArrayList<>();
        for (Long genre : genres) {
            listGenre.add(new GenreEntity(genre, genreService.get(genre).getVersion()));
        }

        VoiceEntity voiceEntity = new VoiceEntity(singerEntity, listGenre, message);

        votingDao.save(voiceEntity);

    }

    @Override
    public List<VoiceDTOFromDB> get() {
        List<VoiceDTOFromDB> result = new ArrayList<>();
        List<VoiceEntity> voiceList = votingDao.getVoices();
        VoiceDTOFromDB.VoiceDTOFromDBBuilder builder = VoiceDTOFromDB.VoiceDTOFromDBBuilder.create();

        for (VoiceEntity voiceEntity : voiceList) {
            SingerEntity singer = voiceEntity.getSinger();
            builder.setSinger(singer.getId());

            List<GenreEntity> genres = voiceEntity.getGenres();
            for (GenreEntity genre : genres) {
                builder.addGenre(genre.getId());
            }

            VoiceDTOFromDB voiceDTOFromDB = builder
                    .setMessage(voiceEntity.getMessage())
                    .setDtCreate(voiceEntity.getDtCreate())
                    .build();

            result.add(voiceDTOFromDB);
        }

        return result;
    }

    private void check(VoiceDTO voice) {
        Long singer = voice.getSinger();
        if (!singerService.isContain(singer)) {
            throw new InvalidInputServiceException("Singer №" + singer + " is not on the list");
        }

        Long[] genres = voice.getGenre();

        Set<Long> setGenre = new HashSet<>();

        for (Long val : genres) {
            setGenre.add(val);
        }

        if (setGenre.size() < 3 || setGenre.size() > 5) {
            throw new InvalidInputServiceException("Wrong number of genres, need from 3 to 5");
        }

        if (setGenre.size() != genres.length) {
            throw new InvalidInputServiceException("Received genres contain duplicates");
        }

        for (Long genre : setGenre) {
            if (!genreService.isContain(genre)) {
                throw new InvalidInputServiceException("Genre №" + genre + " is not on the list");
            }
        }

        String aboutMe = voice.getMessage();
        if (aboutMe == null || aboutMe.isBlank()) {
            throw new InvalidInputServiceException("You need to enter information about yourself");
        }

    }
}
