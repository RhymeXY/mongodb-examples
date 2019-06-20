package xy.study.mongodb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.geo.Distance;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeofenceDistanceDTO extends GeofenceDTO {
    private Distance distance;
}
