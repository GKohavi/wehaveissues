package example.example;

import android.graphics.Bitmap;

import com.google.android.gms.maps.GoogleMap;

import java.util.UUID;

/**
 * Created by aiflab on 10/7/17.
 */

public class Issue {
    private String name;
    private int score;
    private String description;
    private Bitmap pic;
    private double lat;
    private double lon;
    private GoogleMap gMap;

    public UUID mId;

    public Issue() {
        name = "Blank Issue Name";
        score = -1;
        description = "Blank Description";
        pic = null;
        lat = 37.8710; //Default Memorial Stadium Lat
        lon = -122.2508; //Default Memorial Stadium Lon

        mId = UUID.randomUUID();
    }



    //Setter functions

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPic(Bitmap thePic) { this.pic = thePic; }

    public void setLatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void setMap(GoogleMap gMap) {
        this.gMap = gMap;
    }

    //Accessor functions
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getPic() {
        return pic;
    }

    public double getLat() { return lat; }

    public double getLon() { return lon; }

}
