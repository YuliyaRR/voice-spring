package groupwork;

import groupwork.dao.db.GenreDaoDB;
import groupwork.dao.db.SingerDaoDB;
import groupwork.dao.db.VotingDaoDB;
import groupwork.dao.db.orm.manager.Manager;
import groupwork.dto.*;
import groupwork.service.GenreService;
import groupwork.service.SingerService;
import groupwork.service.StatisticsService;
import groupwork.service.VoteService;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IStatisticsService;
import groupwork.service.api.IVotesService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

public class MainSpring {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("main-context.xml");

       // Manager manager = context.getBean("manager", Manager.class);

      //  GenreDaoDB genreDao = context.getBean("genreDao", GenreDaoDB.class);
        IGenreService genreService = context.getBean("genreService", GenreService.class);

     //   SingerDaoDB singerDao = context.getBean("singerDao", SingerDaoDB.class);
        ISingerService singerService = context.getBean("singerService", SingerService.class);

      //  VotingDaoDB votingDao = context.getBean("votingDao", VotingDaoDB.class);
        IVotesService voteService = context.getBean("voteService", VoteService.class);

        IStatisticsService statisticService = context.getBean("statisticService", StatisticsService.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Список исполнителей для голосования");

        List<SingerDTO> singerList = singerService.get();
        singerList.forEach(singerDTO -> System.out.println(singerDTO));

        System.out.println("Проголосуйте за исполнителя, введите его id");
        long idSinger = Long.parseLong(reader.readLine());

        System.out.println("Список жанров для голосования");

        List<GenreDTO> genreList = genreService.get();
        genreList.forEach(genreDTO -> System.out.println(genreDTO));

        System.out.println("Проголосуйте за жанры, выберите 3-5 жанров, введите через запятую");

        String idGenresLine = reader.readLine();
        String[] idGenresArrString = idGenresLine.split(" ");

        long[] genresID = Arrays.stream(idGenresArrString)
                .mapToLong(str -> Long.parseLong(str))
                .toArray();

        System.out.println("Введите информацию о себе");

        String about = reader.readLine();

        System.out.println("Введите email");

        String email = reader.readLine();
        VoiceDTO voiceDTO = new VoiceDTO(idSinger, genresID, about, email);

        voteService.save(voiceDTO);

        System.out.println("Статистика голосования");
        AllStatisticDTO allSort = statisticService.getAllSort();
        Map<SingerDTO, Integer> mapSingers = allSort.getMapSingers();
        Map<GenreDTO, Integer> mapGenres = allSort.getMapGenres();
        Map<LocalDateTime, String> mapUserInfo = allSort.getMapUserInfo();

        System.out.println("Результат голосования за исполнителя");
        for (Map.Entry<SingerDTO, Integer> entry : mapSingers.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        System.out.println("Результат голосования за жанры");
        for (Map.Entry<GenreDTO, Integer> entry : mapGenres.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        System.out.println("Информация о себе");
        for (Map.Entry<LocalDateTime, String> entry : mapUserInfo.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        System.out.println("Добавить нового артиста");
        System.out.println("Введите имя нового артиста");
        String newNameCreate = reader.readLine();
        singerService.create(new SingerDTO(newNameCreate));

        System.out.println("Изменить артиста");
        System.out.println("Введити id артиста, имя которого хотите изменить");
        long idSingerUpdate = Long.parseLong(reader.readLine());

        System.out.println("Введите новое имя артиста");
        String newNameUpdate = reader.readLine();

        singerService.update(idSingerUpdate, new SingerDTO(newNameUpdate));

        System.out.println("Удалить артиста");
        System.out.println("Введити id артиста, которого хотите удалить");

        long idSingerDelete = Long.parseLong(reader.readLine());

        singerService.delete(idSingerDelete);

        System.out.println("Добавить новый жанр");
        System.out.println("Введите название нового жанра");

        String newGenreCreate = reader.readLine();
        genreService.create(new GenreDTO(newGenreCreate));

        System.out.println("Изменить жанр");
        System.out.println("Введити id жанра, который хотите изменить");

        long idGenreUpdate = Long.parseLong(reader.readLine());
        System.out.println("Введите новое название жанра");
        String newNameGenreUpdate = reader.readLine();

        genreService.update(idGenreUpdate, new GenreDTO(newNameGenreUpdate));

        System.out.println("Удалить жанр");
        System.out.println("Введити id жанра, который хотите удалить");

        long idGenreDelete = Long.parseLong(reader.readLine());

        genreService.delete(idGenreDelete);

    }
}
