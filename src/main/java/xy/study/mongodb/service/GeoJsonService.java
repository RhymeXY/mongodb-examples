package xy.study.mongodb.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xy.study.mongodb.config.GeoJsonType;
import xy.study.mongodb.config.MongoDBCollections;
import xy.study.mongodb.dao.GeoJsonDAO;
import xy.study.mongodb.dto.GeofenceRequest;
import xy.study.mongodb.dto.Location;
import xy.study.mongodb.pojo.Geofence;

import java.util.List;

@Service
@Slf4j
public class GeoJsonService {

    @Autowired
    private GeoJsonDAO geoJsonDAO;

    public Object insert(GeofenceRequest request) {
        Assert.notNull(request, "request is null");
        Assert.hasText(request.getName(), "name is null");
        Assert.hasText(request.getType(), "type is null");
        Assert.notNull(request.getCoordinates(), "coordinates is null");
        if (GeoJsonType.POLYGON.equalsIgnoreCase(request.getType())) {
            insertPolygon(request);
        }
        return geoJsonDAO.findByNameReturnObject(request.getName(), MongoDBCollections.GEOFENCE);
    }

    private void insertPolygon(GeofenceRequest request) {
        List<Point> points = JSONObject.parseObject(JSONObject.toJSONString(request.getCoordinates()), new TypeReference<List<Point>>() {
        });
        GeoJsonPolygon geoJsonPolygon = new GeoJsonPolygon(points);
        Geofence geofence = new Geofence();
        geofence.setName(request.getName());
        geofence.setType(request.getType());
        geofence.setGeo(geoJsonPolygon);

        geoJsonDAO.insert(geofence, MongoDBCollections.GEOFENCE);

    }

    public Object find(String name) {
        return geoJsonDAO.findByNameReturnObject(name, MongoDBCollections.GEOFENCE);
    }

    public Object in(ObjectId id, Location location) {
        boolean b = geoJsonDAO.in(id, location);
        return b;
    }
}
