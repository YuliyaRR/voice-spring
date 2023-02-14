package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "app.artists")
public class SingerEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private String name;

    @Version
    private Long version;

    public SingerEntity() {
    }

    public SingerEntity(Long id, String name, Long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public SingerEntity(String name) {
        this.name = name;
    }

    public SingerEntity(Long id, Long version) {
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVersion() {
        return version;
    }
}
