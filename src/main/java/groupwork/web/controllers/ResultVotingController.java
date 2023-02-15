package groupwork.web.controllers;


import groupwork.dto.*;
import groupwork.service.api.IStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/result")
public class ResultVotingController {
    private final IStatisticsService statisticService;

    public ResultVotingController(IStatisticsService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public AllStatisticDTOJSON getAllStatistic() {
        AllStatisticDTO statistic = statisticService.getStatistic();

        List<ResultViewStatistic> singerStatistic = statistic.getSingers().stream()
                .map(dto -> new ResultViewStatistic(dto.getItem().getName(), dto.getItem().getId(), dto.getCountVoice()))
                .collect(Collectors.toList());
        List<ResultViewStatistic> genreStatistic = statistic.getGenres().stream()
                .map(dto -> new ResultViewStatistic(dto.getItem().getName(), dto.getItem().getId(), dto.getCountVoice()))
                .collect(Collectors.toList());

        return new AllStatisticDTOJSON(singerStatistic, genreStatistic, statistic.getAboutUsers());
    }

    @RequestMapping(path = "/singer", method = RequestMethod.GET)
    public List<ResultViewStatistic> getSingerStatistic(){
         return statisticService.getSortSinger().stream()
                .map(dto -> new ResultViewStatistic(dto.getItem().getName(), dto.getItem().getId(), dto.getCountVoice()))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/genre", method = RequestMethod.GET)
    public List<ResultViewStatistic> getGenreStatistic(){
        return statisticService.getSortGenre().stream()
                .map(dto -> new ResultViewStatistic(dto.getItem().getName(), dto.getItem().getId(), dto.getCountVoice()))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public List<AboutUserDTOFromDB> getUserStatistic(){
        return statisticService.getSortAbout();
    }
}
