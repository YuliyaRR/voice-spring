package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app.artists")
public class SingerEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    private String name;


    public SingerEntity() {
    }

    public SingerEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SingerEntity(String name) {
        this.name = name;
    }

    public SingerEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
