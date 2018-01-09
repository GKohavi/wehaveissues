package example.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by aiflab on 10/7/17.
 */

public class Issue {
//    public UUID mId;
    private String mId;

    private String name;
    private int score;
    private String description;
    private String stringPic;
    private double lat;
    private double lon;


    public Issue() {
//        mId = UUID.randomUUID().toString();
        mId = "";
        name = "Blank Issue Name";
        score = 0;
        description = "Blank Description";
        stringPic = null;
        lat = 37.8710; //Default Memorial Stadium Lat
        lon = -122.2508; //Default Memorial Stadium Lon
    }



    //Setter functions
    public void setMId(String mId) {
//        this.mId = mId;
        this.mId = mId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStringPic(String aStringPic) {
        this.stringPic = aStringPic;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    //Accessor functions
    public String getMId() {
        return mId;
    }
    public String getName() {
        return name;
    }
    public double getScore() {
        return score;
    }
    public String getDescription() {
        return description;
    }
    public String getStringPic() {
        return stringPic;
    }
    public double getLat() { return lat; }
    public double getLon() { return lon; }

    public void setStringPicWithBitmap(Bitmap aPic) {
        stringPic = encodeBitmap(aPic); //.substring(0,20);
    }

    public Bitmap getBitmapPic() {
        if (stringPic == null) {
            return null;
        }
        Bitmap ans = decodeStringPic(stringPic);
        if (ans == null) {
//            Do something here!
        }
        return ans;
    }

    // Other utility functions
    public void increaseScore() {
        score += 1;
    }

    //Database function
    public HashMap<String, Object> issueToMap() {
        HashMap<String, Object> ans = new HashMap<>();
        ans.put("mId", mId);
        ans.put("name", name);
        ans.put("score", score);
        ans.put("description", description);
        ans.put("stringPic", stringPic);
        ans.put("lat", lat);
        ans.put("lon", lon);
        return ans;
    }

    private String encodeBitmap(Bitmap aPic) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        aPic.compress(Bitmap.CompressFormat.PNG, 10, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
    private Bitmap decodeStringPic(String aString) {
        byte[] decodedByteArray = android.util.Base64.decode(aString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
