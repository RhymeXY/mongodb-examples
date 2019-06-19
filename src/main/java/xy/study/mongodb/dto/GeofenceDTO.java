package xy.study.mongodb.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class GeofenceDTO<T> {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private String type;
    private GeoJsonDTO<T> geo;
}
