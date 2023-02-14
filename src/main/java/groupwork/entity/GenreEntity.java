package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "app.genres")
public class GenreEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Version
    private Long version;

    private String name;


    public GenreEntity() {
    }

    public GenreEntity(Long id, Long version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    public GenreEntity(Long id, Long version) {
        this.id = id;
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}