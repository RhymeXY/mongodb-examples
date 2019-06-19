package xy.study.mongodb.dto;

import lombok.Data;

@Data
public class GeoJsonDTO<T> {

    private String type;
    private T coordinates;
}
