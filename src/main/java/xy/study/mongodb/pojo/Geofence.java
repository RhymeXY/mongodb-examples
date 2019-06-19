package xy.study.mongodb.pojo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import xy.study.mongodb.config.MongoDBCollections;

@Data
@Document(MongoDBCollections.GEOFENCE)
@CompoundIndexes({
        @CompoundIndex(name = "geo_index", def = "{'geo': '2dsphere'}"),
})
public class Geofence {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private String type;
    private GeoJson geo;
}
