package data;

public class Ligne {

    private String id;
    private String gtfsId;
    private String shortName;
    private String longName;
    private String color;
    private String textColor;
    private String mode;
    private String type;


    public Ligne(String id, String gtfsId, String shortName, String longName, String color, String textColor, String mode, String type) {
        this.id = id;
        this.gtfsId = gtfsId;
        this.shortName = shortName;
        this.longName = longName;
        this.color = color;
        this.textColor = textColor;
        this.mode = mode;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getGtfsId() {
        return gtfsId;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getColor() {
        return color;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getMode() {
        return mode;
    }

    public String getType() {
        return type;
    }
}
