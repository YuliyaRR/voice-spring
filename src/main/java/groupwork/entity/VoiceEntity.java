package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app.votes")
public class VoiceEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "app.votes_singer",
            joinColumns = @JoinColumn(name = "vote_id"))
    private SingerEntity singer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app.votes_genres",
            joinColumns = @JoinColumn(name = "vote_id"))
    private List<GenreEntity> genres;

    private String message;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    public VoiceEntity() {
    }

    public VoiceEntity(SingerEntity singer, List<GenreEntity> genres, String message) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.dtCreate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public SingerEntity getSinger() {
        return singer;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }
}
