package groupwork.dto;

import java.util.Objects;

public class SingerDTOFromDBWithoutVersion {
    private String name;
    private Long id;

    public SingerDTOFromDBWithoutVersion(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerDTOFromDBWithoutVersion that = (SingerDTOFromDBWithoutVersion) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
