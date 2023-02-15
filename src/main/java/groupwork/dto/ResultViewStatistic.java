package groupwork.dto;

public class ResultViewStatistic {
    private String name;
    private Long id;
    private Integer count;

    public ResultViewStatistic(String name, Long id, Integer count) {
        this.name = name;
        this.id = id;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
