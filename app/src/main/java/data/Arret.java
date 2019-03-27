package data;

public class Arret {

    private String id;
    private String code;
    private String city;
    private String name;
    private double lat;
    private double lon;


    public Arret(String id, String code, String city, String name, double lat, double lon) {
        this.id = id;
        this.code = code;
        this.city = city;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
