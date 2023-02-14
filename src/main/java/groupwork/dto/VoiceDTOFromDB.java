package groupwork.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VoiceDTOFromDB {
    private Long singerID;
    private List<Long> genresID;
    private String message;
    private LocalDateTime dtCreate;

    public VoiceDTOFromDB(Long singerID, List<Long> genresID, String message, LocalDateTime dtCreate) {
        this.singerID = singerID;
        this.genresID = genresID;
        this.message = message;
        this.dtCreate = dtCreate;
    }

    public Long getSingerID() {
        return singerID;
    }

    public List<Long> getGenresID() {
        return genresID;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Override
    public String toString() {
        return "VoiceDTOFromDB{" +
                "singerID=" + singerID +
                ", genresID=" + genresID +
                ", message='" + message + '\'' +
                ", dtCreate=" + dtCreate +
                '}';
    }


    public static class VoiceDTOFromDBBuilder {
        private Long singer;
        private List<Long> genres = new ArrayList<>();
        private String message;
        private LocalDateTime dtCreate;

        private VoiceDTOFromDBBuilder() {
        }

        public static VoiceDTOFromDBBuilder create(){
            return new VoiceDTOFromDBBuilder();
        }

        public VoiceDTOFromDBBuilder setSinger(Long singer) {
            this.singer = singer;
            return this;
        }

        public VoiceDTOFromDBBuilder setGenres(List<Long> genres) {
            this.genres = genres;
            return this;
        }

        public VoiceDTOFromDBBuilder addGenre(Long genre){
            this.genres.add(genre);
            return this;
        }

        public VoiceDTOFromDBBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public VoiceDTOFromDBBuilder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        private void clearListGenres(){
            this.genres.clear();
        }

        public VoiceDTOFromDB build(){
            List<Long>copyGenres = List.copyOf(this.genres);
            clearListGenres();
            return new VoiceDTOFromDB(singer, copyGenres, message, dtCreate);
        }
    }
}
