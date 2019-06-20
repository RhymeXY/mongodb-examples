package xy.study.mongodb.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class GeofenceDTO<T> {
    @Id
    ObjectId id;

    @Indexed(unique = true)
    String name;

    String type;
    GeoJsonDTO<T> geo;
}
