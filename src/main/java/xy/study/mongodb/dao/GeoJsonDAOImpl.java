package xy.study.mongodb.dao;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import xy.study.mongodb.config.MongoDBCollections;
import xy.study.mongodb.dto.GeofenceDTO;
import xy.study.mongodb.dto.Location;
import xy.study.mongodb.pojo.Geofence;

@Repository
@Slf4j
public class GeoJsonDAOImpl implements GeoJsonDAO {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void insert(Geofence geoJson, String collectionName) {
        mongoTemplate.insert(geoJson, collectionName);
    }

    @Override
    public Geofence findByName(String name, String collectionName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), Geofence.class, collectionName);
    }

    @Override
    public Document findByNameReturnDocument(String name, String collectionName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), Document.class, collectionName);
    }

    @Override
    public Object findByNameReturnObject(String name, String collectionName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), GeofenceDTO.class, collectionName);
    }

    @Override
    public boolean in(ObjectId id, Location location) {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(location.getLongitude(), location.getLatitude());
        Query query = Query.query(Criteria.where("geo").intersects(geoJsonPoint).and("_id").is(id));

        log.info("in query : {}", query.toString());
        return mongoTemplate.count(query, MongoDBCollections.GEOFENCE) > 0;
    }


}
