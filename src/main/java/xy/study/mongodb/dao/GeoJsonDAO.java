package xy.study.mongodb.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import xy.study.mongodb.dto.GeoJsonPointQuery;
import xy.study.mongodb.dto.GeofenceDTO;
import xy.study.mongodb.pojo.Geofence;

import java.util.List;

public interface GeoJsonDAO {
    void insert(Geofence geoJson, String collectionName);

    Geofence findByName(String name, String collectionName);

    Document findByNameReturnDocument(String name, String collectionName);

    Object findByNameReturnObject(String name, String collectionName);

    boolean in(ObjectId id, GeoJsonPointQuery pointQuery);

    List<GeofenceDTO> in(GeoJsonPointQuery pointQuery);

    List<GeofenceDTO> near(GeoJsonPointQuery pointQuery);
}
