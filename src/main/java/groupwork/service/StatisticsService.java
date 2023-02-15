package groupwork.service;

import groupwork.dto.*;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IStatisticsService;
import groupwork.service.api.IVotesService;

import java.util.*;
import java.util.stream.Collectors;


public class StatisticsService implements IStatisticsService {

    private final IVotesService voteService;
    private final ISingerService singerService;
    private final IGenreService genreService;


    public StatisticsService(IVotesService voteService,
                             ISingerService singerService,
                             IGenreService genreService) {
        this.voteService = voteService;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    public List<VoteCounterRaw<SingerDTOBrief>> getSortSinger(){
        List<VoteCounterRaw<SingerDTOBrief>> result = singerService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        for (VoiceDTOFromDB voice : voteService.get()) {
            Long singerID = voice.getSingerID();
            for (VoteCounterRaw<SingerDTOBrief> singer : result) {
                if(singer.getItem().getId().equals(singerID)) {
                    singer.addVoice();
                    break;
                }
            }
        }

        result.sort((o1, o2)-> o2.getCountVoice() - o1.getCountVoice());

        return result;
    }

    public List<VoteCounterRaw<GenreDTOBrief>> getSortGenre(){
        List<VoteCounterRaw<GenreDTOBrief>> result = genreService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        for (VoiceDTOFromDB voice : voteService.get()) {
            List<Long> genres = voice.getGenresID();

            for (VoteCounterRaw<GenreDTOBrief> resultGenre : result) {
                for (Long genre : genres) {
                    if (resultGenre.getItem().getId().equals(genre)){
                        resultGenre.addVoice();
                        break;
                    }
                }
            }
        }

        result.sort((o1, o2) -> o2.getCountVoice() - o1.getCountVoice());

        return result;

    }

    public List<AboutUserDTOFromDB> getSortAbout() {
        return voteService.get().stream()
                .map(o -> new AboutUserDTOFromDB(o.getMessage(), o.getDtCreate()))
                .sorted((o1, o2) -> o2.getCreationTime().compareTo(o1.getCreationTime()))
                .collect(Collectors.toList());
    }

    public AllStatisticDTO getStatistic() {
        List<VoiceDTOFromDB> voiceList = voteService.get();

        List<VoteCounterRaw<SingerDTOBrief>> singerList = singerService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        List<VoteCounterRaw<GenreDTOBrief>> genreList = genreService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        List<AboutUserDTOFromDB> aboutUserList = new ArrayList<>();

        for (VoiceDTOFromDB voice : voiceList) {
            Long singerID = voice.getSingerID();
            List<Long> genresID = voice.getGenresID();

            for (VoteCounterRaw<SingerDTOBrief> singer : singerList) {
                if(singer.getItem().getId().equals(singerID)){
                    singer.addVoice();
                    break;
                }
            }

            for (VoteCounterRaw<GenreDTOBrief> genre : genreList) {
                for (Long genreID : genresID) {
                    if(genre.getItem().getId().equals(genreID)){
                        genre.addVoice();
                        break;
                    }
                }
            }

            aboutUserList.add(new AboutUserDTOFromDB(voice.getMessage(), voice.getDtCreate()));

        }

        singerList.sort(((o1, o2) -> o2.getCountVoice() - o1.getCountVoice()));
        genreList.sort(((o1, o2) -> o2.getCountVoice() - o1.getCountVoice()));
        aboutUserList.sort(((o1, o2) -> o2.getCreationTime().compareTo(o1.getCreationTime())));

        return new AllStatisticDTO(singerList, genreList, aboutUserList);
    }
}
