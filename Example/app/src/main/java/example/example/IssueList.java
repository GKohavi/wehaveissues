package example.example;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssueList {
    private static IssueList sIssueList;
    private ArrayList<Issue> mIssues;

    private IssueList(Context appContext) {
        mIssues = new ArrayList<Issue>();

        Issue tempIssue = new Issue();
        tempIssue.setName("Raised Sidewalk");
        tempIssue.setDescription("This raised sidewalk near Memorial Stadium is a hazard to pedestrians - especially those with impairments. Please help us FixIt!");
        Drawable tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_sidewalk);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8710, -122.2508); //Location of Memorial Stadium
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Confusing Parking Signs");
        tempIssue.setDescription("These parking signs are way too confusing to read and follow. It would be much nicer to have a single sign with clear instructions that we can follow accordingly. Please help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.confusing_parking_signs);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8705, -122.2606); //Location of Dwinelle
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Broken Street Lamps");
        tempIssue.setDescription("It's too dark on this street and I feel unsafe walking back home after work at night. It would be awesome if the city could help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_street_lights);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8737,-122.2578); //Location of Evan
        mIssues.add(tempIssue);
    }

    public static IssueList get(Context c) {
        if (sIssueList == null) {
            sIssueList = new IssueList(c.getApplicationContext());
        }
        return sIssueList;
    }

    public ArrayList<Issue> getIssues() {
        return mIssues;
    }

    public Issue getIssue(UUID id) {
        for (int i = 0; i < mIssues.size(); i++) {
            if (mIssues.get(i).mId.equals(id)) {
                return mIssues.get(i);
            }
        }
        return null;
    }
}
