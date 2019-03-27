package data;

public class HorraireArret {

    private String stopId;
    private String stopName;
    private int arriveePrevue;
    private int departPrevue;
    private int arriveeReel;
    private int departReel;
    private int arriveeRetard;
    private int departRetard;
    private boolean timepoint;
    private boolean realtime;


    public HorraireArret(String stopId, String stopName, int arriveePrevue, int departPrevue, int arriveeReel, int departReel, int arriveeRetard, int departRetard, boolean timepoint, boolean realtime) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.arriveePrevue = arriveePrevue;
        this.departPrevue = departPrevue;
        this.arriveeReel = arriveeReel;
        this.departReel = departReel;
        this.arriveeRetard = arriveeRetard;
        this.departRetard = departRetard;
        this.timepoint = timepoint;
        this.realtime = realtime;
    }


    public String getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public int getArriveePrevue() {
        return arriveePrevue;
    }

    public int getDepartPrevue() {
        return departPrevue;
    }

    public int getArriveeReel() {
        return arriveeReel;
    }

    public int getDepartReel() {
        return departReel;
    }

    public int getArriveeRetard() {
        return arriveeRetard;
    }

    public int getDepartRetard() {
        return departRetard;
    }

    public boolean isTimepoint() {
        return timepoint;
    }

    public boolean isRealtime() {
        return realtime;
    }

}
