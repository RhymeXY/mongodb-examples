package xy.study.mongodb.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xy.study.mongodb.dto.GeofenceRequest;
import xy.study.mongodb.dto.Location;
import xy.study.mongodb.service.GeoJsonService;

@RequestMapping(value = "/api/geos")
@RestController
public class GeoJsonController {

    @Autowired
    private GeoJsonService geoJsonService;

    @PostMapping
    public Object create(@RequestBody GeofenceRequest request) {
        return geoJsonService.insert(request);
    }

    @GetMapping
    public Object find(@RequestParam(value = "name") String name) {
        return geoJsonService.find(name);
    }

    @GetMapping(value = "/in/{id:[a-zA-Z0-9]{0,24}}")
    public Object in(@PathVariable("id") ObjectId id,
                     Location location) {

        return geoJsonService.in(id, location);
    }
}
