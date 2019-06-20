package xy.study.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xy.study.mongodb.dto.GeoJsonPointQuery;
import xy.study.mongodb.dto.GeofenceRequest;
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
    public Object in(@PathVariable("id") String id,
                     GeoJsonPointQuery query) {

        return geoJsonService.in(id, query);
    }

    @GetMapping(value = "/in")
    public Object in(GeoJsonPointQuery query) {

        return geoJsonService.in(query);
    }

    @GetMapping(value = "/near")
    public Object near(GeoJsonPointQuery query) {

        return geoJsonService.near(query);
    }
}
