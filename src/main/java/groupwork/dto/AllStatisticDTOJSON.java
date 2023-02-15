package groupwork.dto;

import java.util.List;

public class AllStatisticDTOJSON {

    private final List<ResultViewStatistic> singers;
    private final List<ResultViewStatistic> genres;
    private final List<AboutUserDTOFromDB> aboutUsers;

    public AllStatisticDTOJSON(List<ResultViewStatistic> singers,
                               List<ResultViewStatistic> genres,
                               List<AboutUserDTOFromDB> aboutUsers) {
        this.singers = singers;
        this.genres = genres;
        this.aboutUsers = aboutUsers;
    }

    public List<ResultViewStatistic> getSingers() {
        return singers;
    }

    public List<ResultViewStatistic> getGenres() {
        return genres;
    }

    public List<AboutUserDTOFromDB> getAboutUsers() {
        return aboutUsers;
    }
}
