package xy.study.mongodb.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import xy.study.mongodb.dto.Location;
import xy.study.mongodb.pojo.Geofence;

public interface GeoJsonDAO {
    void insert(Geofence geoJson, String collectionName);

    Geofence findByName(String name, String collectionName);

    Document findByNameReturnDocument(String name, String collectionName);

    Object findByNameReturnObject(String name, String collectionName);

    boolean in(ObjectId id, Location location);
}
