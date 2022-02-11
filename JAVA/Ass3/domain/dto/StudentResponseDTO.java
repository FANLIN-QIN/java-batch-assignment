package RESTdemo.domain.dto;

public class StudentResponseDTO {
    private String name;

    public StudentResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
