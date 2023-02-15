package groupwork.service.api;

import groupwork.dto.*;

import java.util.List;

public interface IStatisticsService {

    AllStatisticDTO getStatistic();

    List<VoteCounterRaw<SingerDTOBrief>> getSortSinger();

    List<VoteCounterRaw<GenreDTOBrief>> getSortGenre();

    List<AboutUserDTOFromDB> getSortAbout();

}
