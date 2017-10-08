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
        tempIssue.setDescription("This raised sidewalk near Li Ka Shing is a hazard to pedestrians - especially those with impairments. Please help us FixIt!");
        Drawable tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_sidewalk);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.873418, -122.265070); //Location of Li Ka Shing
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Parking Sign Overlap");
        tempIssue.setDescription("These parking signs are way too confusing to read and follow. There are too many, and they seem to overlap. It would be much nicer to have a single sign with clear instructions that we can follow accordingly. Please help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.confusing_parking_signs);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8682753,-122.2543885); //Location of Haste Street / People's Park
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Broken Street Lamps");
        tempIssue.setDescription("It's too dark on this street and I feel unsafe walking back home after work at night. It would be awesome if the city could help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_street_lights);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8762932,-122.2589373); //Location of Le Roy and Ridge Road intersection
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Bathroom Graffiti");
        tempIssue.setDescription("There's a lot of graffiti in the bathroom stalls in Wurster. The stalls seem kinda run-down. Would it be possible to FixIt?");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.graffiti);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8706, -122.2547); // wurster
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Broken Glass Window");
        tempIssue.setDescription("I'm worried that this broken glass creates an unsafe working environment for employees here! Plus it compromises security. Help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_glass);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8692459,-122.2616494); //Amazon store
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Incomplete Trail Lining");
        tempIssue.setDescription("It seems like there's an incomplete trail being cared out on Memorial Glade. There are some strips of wood maybe meant to line the path? It would be awesome if the University Officials could FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.incomplete_trail_lining);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8733785,-122.2589375); // Memorial Glade!
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Ants in Foothill Dining");
        tempIssue.setDescription("There are a lot of ants in the Foothill Dining Commons. Not only is this a concern because there are ants near the food, but it is also concerning to me because I am scared of ants! Please someone FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.ants);
        tempIssue.setPic(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8752884,-122.2557182); // Foothill!
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
