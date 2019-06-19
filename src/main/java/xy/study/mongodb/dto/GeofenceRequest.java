package xy.study.mongodb.dto;

import lombok.Data;

@Data
public class GeofenceRequest<T>{
    private String name;
    private String type;
    private T coordinates;

}
