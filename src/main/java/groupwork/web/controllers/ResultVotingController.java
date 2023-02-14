package groupwork.web.controllers;


import groupwork.dto.*;
import groupwork.service.api.IStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultVotingController {
    private final IStatisticsService statisticService;

    public ResultVotingController(IStatisticsService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public AllStatisticDTO getAllStatistic() {
        return statisticService.getStatistic();
    }

    @RequestMapping(path = "/singer", method = RequestMethod.GET)
    public List<VoteCounterRaw<SingerDTOFromDBWithoutVersion>> getSingerStatistic(){
        return statisticService.getSortSinger();
    }

    @RequestMapping(path = "/genre", method = RequestMethod.GET)
    public List<VoteCounterRaw<GenreDTOFromDBWithoutVersion>> getGenreStatistic(){
        return statisticService.getSortGenre();
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public List<AboutUserDTOFromDB> getUserStatistic(){
        return statisticService.getSortAbout();
    }
}
