package groupwork.service.api;

import groupwork.dto.*;

import java.util.List;

public interface IStatisticsService {

    AllStatisticDTO getStatistic();

    List<VoteCounterRaw<SingerDTOFromDBWithoutVersion>> getSortSinger();

    List<VoteCounterRaw<GenreDTOFromDBWithoutVersion>> getSortGenre();

    List<AboutUserDTOFromDB> getSortAbout();

}
