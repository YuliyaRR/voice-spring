package groupwork.dto;

import java.util.List;

public class AllStatisticDTO {

    private final List<VoteCounterRaw<SingerDTOFromDBWithoutVersion>> singers;
    private final List<VoteCounterRaw<GenreDTOFromDBWithoutVersion>> genres;
    private final List<AboutUserDTOFromDB> aboutUsers;

    public AllStatisticDTO(List<VoteCounterRaw<SingerDTOFromDBWithoutVersion>> singers,
                           List<VoteCounterRaw<GenreDTOFromDBWithoutVersion>> genres,
                           List<AboutUserDTOFromDB> aboutUsers) {
        this.singers = singers;
        this.genres = genres;
        this.aboutUsers = aboutUsers;
    }

    public List<VoteCounterRaw<SingerDTOFromDBWithoutVersion>> getSingers() {
        return singers;
    }

    public List<VoteCounterRaw<GenreDTOFromDBWithoutVersion>> getGenres() {
        return genres;
    }

    public List<AboutUserDTOFromDB> getAboutUsers() {
        return aboutUsers;
    }

}
