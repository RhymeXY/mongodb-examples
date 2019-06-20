package xy.study.mongodb.utils;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;

public class DistanceConvert {

    /**
     * 将传入的Distance对象返回成km对象
     * @param distance
     * @return
     */
    public static Distance toKm(Distance distance) {
        Metric metric = distance.getMetric();
        if (Metrics.KILOMETERS.equals(metric)) {
            return new Distance(distance.getValue(), Metrics.KILOMETERS);
        } else if (Metrics.MILES.equals(metric)) {
            return new Distance(distance.getValue() * Metrics.KILOMETERS.getMultiplier() / Metrics.MILES.getMultiplier(), Metrics.KILOMETERS);
        } else if (Metrics.NEUTRAL.equals(metric)) {
            return new Distance(distance.getValue() * Metrics.KILOMETERS.getMultiplier() / Metrics.NEUTRAL.getMultiplier(), Metrics.KILOMETERS);
        } else {
            throw new IllegalArgumentException("Unknown metric: " + metric);
        }
    }
}
