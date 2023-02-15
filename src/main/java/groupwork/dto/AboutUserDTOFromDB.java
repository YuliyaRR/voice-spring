package groupwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class AboutUserDTOFromDB {
    private String aboutUser;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationTime;

    public AboutUserDTOFromDB(String aboutUser, LocalDateTime creationTime) {
        this.aboutUser = aboutUser;
        this.creationTime = creationTime;
    }

    public String getAboutUser() {
        return aboutUser;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AboutUserDTOFromDB that = (AboutUserDTOFromDB) o;
        return Objects.equals(aboutUser, that.aboutUser) && Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aboutUser, creationTime);
    }
}
