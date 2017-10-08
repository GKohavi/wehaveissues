package example.example;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by aiflab on 10/7/17.
 */

public class Issue {
    private String name;
    private int score;
    private String description;
    private Bitmap[] pic;

    public UUID mId;

    public Issue() {
        name = "Blank Issue Name";
        score = -1;
        description = "Blank Description";
        pic = null;

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

    public void setPic(Bitmap[] thePic) {
        this.pic = thePic;
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

    public Bitmap[] getPic() {
        return pic;
    }
}
