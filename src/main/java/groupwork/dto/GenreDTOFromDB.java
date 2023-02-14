package groupwork.dto;

import java.util.Objects;

public class GenreDTOFromDB {
    private String name;
    private Long id;
    private Long version;

    public GenreDTOFromDB(String name, Long id, Long version) {
        this.name = name;
        this.id = id;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTOFromDB that = (GenreDTOFromDB) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, version);
    }

    @Override
    public String toString() {
        return "name = " + name + ", id = " + id + ", version = " + version;
    }
}
