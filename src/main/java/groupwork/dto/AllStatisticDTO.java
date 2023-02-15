package groupwork.dto;

import java.util.List;

public class AllStatisticDTO {

    private final List<VoteCounterRaw<SingerDTOBrief>> singers;
    private final List<VoteCounterRaw<GenreDTOBrief>> genres;
    private final List<AboutUserDTOFromDB> aboutUsers;

    public AllStatisticDTO(List<VoteCounterRaw<SingerDTOBrief>> singers,
                           List<VoteCounterRaw<GenreDTOBrief>> genres,
                           List<AboutUserDTOFromDB> aboutUsers) {
        this.singers = singers;
        this.genres = genres;
        this.aboutUsers = aboutUsers;
    }

    public List<VoteCounterRaw<SingerDTOBrief>> getSingers() {
        return singers;
    }

    public List<VoteCounterRaw<GenreDTOBrief>> getGenres() {
        return genres;
    }

    public List<AboutUserDTOFromDB> getAboutUsers() {
        return aboutUsers;
    }

}
