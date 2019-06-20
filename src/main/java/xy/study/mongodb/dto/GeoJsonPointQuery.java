package xy.study.mongodb.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Data
public class GeoJsonPointQuery {
    private String name;

    private Double x;
    private Double y;

    private Double minDistance;
    private Double maxDistance;

    public Query buildInQuery() {
        validate();
        Query query = new Query();
        query.addCriteria(name());
        query.addCriteria(in());

        return query;
    }

    private void validate() {
        Assert.notNull(x, "x must not be null");
        Assert.notNull(y, "y must not be null");
    }

    public Query buildNearQuery() {
        Query query = new Query();
        query.addCriteria(name());
        query.addCriteria(near());

        return query;
    }

    private CriteriaDefinition near() {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(x, y);
        Criteria near = Criteria.where("geo").near(geoJsonPoint);

        /*
         StackOverflow上：https://stackoverflow.com/questions/27228039/mongodb-spring-data-geonear-query-with-max-and-min-distance
         似乎SpringDataMongoDB没有$minDistance的运算
          */
        near = ObjectUtils.isEmpty(minDistance) ? near : near.minDistance(minDistance);
        near = ObjectUtils.isEmpty(maxDistance) ? near : near.maxDistance(maxDistance);
        return near;
    }

    private Criteria in() {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(x, y);
        return Criteria.where("geo").intersects(geoJsonPoint);
    }

    public Criteria name() {
        return StringUtils.hasText(name) ? where("name").is(name) : new Criteria();
    }

}
