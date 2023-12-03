import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class GeoLocation {
    private double lat;
    private double lon;
    private static int numLocations = 0;
    GeoLocation() {
        Random rand = new Random();
        double l1 = rand.nextDouble() * 180 - 90;
        double l2 = rand.nextDouble() * 180 - 90;
        BigDecimal bd1 = new BigDecimal(l1).setScale(6, RoundingMode.HALF_UP);
        BigDecimal bd2 = new BigDecimal(l2).setScale(6, RoundingMode.HALF_UP);
        lat = bd1.doubleValue();
        lon = bd2.doubleValue();
        numLocations += 1;
    }
    GeoLocation(double latitude, double longitude) {
        lat = latitude;
        lon = longitude;
        numLocations += 1;
    }
    GeoLocation(GeoLocation geo1) {
        Random rand = new Random();
        double l1 = geo1.lat + (rand.nextDouble() * 2 - 1) / 10;
        double l2 = geo1.lon + (rand.nextDouble() * 2 - 1) / 10;
        BigDecimal bd1 = new BigDecimal(l1).setScale(6, RoundingMode.HALF_UP);
        BigDecimal bd2 = new BigDecimal(l2).setScale(6, RoundingMode.HALF_UP);
        lat = bd1.doubleValue();
        lon = bd2.doubleValue();
        numLocations += 1;

    }
    public void print() {
        System.out.println("[" + lat + ", " + lon + "]");
    }
    public static double distance(GeoLocation geo1, GeoLocation geo2) {
        double dLat = Math.toRadians(geo2.lat - geo1.lat);
        double dLon = Math.toRadians(geo2.lon - geo1.lon);

        // convert to radians
        double lat1 = Math.toRadians(geo1.lat);
        double lat2 = Math.toRadians(geo2.lat);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        BigDecimal dist = new BigDecimal(rad * c).setScale(1, RoundingMode.HALF_EVEN);
        return dist.doubleValue();
    }
    public static int getNumLocations() {
        return numLocations;
    }
}
