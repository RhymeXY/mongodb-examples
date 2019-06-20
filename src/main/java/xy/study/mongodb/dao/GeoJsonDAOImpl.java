package xy.study.mongodb.dao;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import xy.study.mongodb.config.MongoDBCollections;
import xy.study.mongodb.dto.GeoJsonPointQuery;
import xy.study.mongodb.dto.GeofenceDTO;
import xy.study.mongodb.pojo.Geofence;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
        return mongoTemplate.findOne(Query.query(where("name").is(name)), Geofence.class, collectionName);
    }

    @Override
    public Document findByNameReturnDocument(String name, String collectionName) {
        return mongoTemplate.findOne(Query.query(where("name").is(name)), Document.class, collectionName);
    }

    @Override
    public Object findByNameReturnObject(String name, String collectionName) {
        return mongoTemplate.findOne(Query.query(where("name").is(name)), GeofenceDTO.class, collectionName);
    }

    @Override
    public boolean in(ObjectId id, GeoJsonPointQuery pointQuery) {
        Query query = pointQuery.buildInQuery();
        query.addCriteria(where("_id").is(id));
        log.info("in query : {}", query.toString());
        return mongoTemplate.count(query, MongoDBCollections.GEOFENCE) > 0;
    }

    @Override
    public List<GeofenceDTO> in(GeoJsonPointQuery pointQuery) {
        Query query = pointQuery.buildInQuery();
        log.info("in query : {}", query.toString());
        return mongoTemplate.find(query, GeofenceDTO.class, MongoDBCollections.GEOFENCE);
    }

    @Override
    public List<GeofenceDTO> near(GeoJsonPointQuery pointQuery) {
        Query query = pointQuery.buildNearQuery();
        log.info("near query : {}", query.toString());
        return mongoTemplate.find(query, GeofenceDTO.class, MongoDBCollections.GEOFENCE);
    }

}
