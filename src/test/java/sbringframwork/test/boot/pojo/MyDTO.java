package sbringframwork.test.boot.pojo;

import lombok.Data;

/**
 *
 */
@Data
public class MyDTO {
    private Integer id;
    private String name;

    public MyDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MyDTO() {
    }
}
