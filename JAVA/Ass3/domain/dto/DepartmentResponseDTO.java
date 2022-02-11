package RESTdemo.domain.dto;

public class DepartmentResponseDTO {
    private String name;

    public DepartmentResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
