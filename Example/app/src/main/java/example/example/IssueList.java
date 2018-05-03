package example.example;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssueList {
    private static IssueList sIssueList;
    private ArrayList<Issue> mIssues;

    private IssueList(Context appContext) {
        mIssues = new ArrayList<>();

        createStartingIssues(appContext);

//        getStartingIssues();
//        Toast.makeText(appContext,"Loading Issues...", Toast.LENGTH_SHORT).show();

//        createStartingIssues(appContext);

//        DatabaseReference aDatabase = FirebaseDatabase.getInstance().getReference();
//        for (Issue anIssue : mIssues) {
//            String tempKey = aDatabase.push().getKey();
//            anIssue.setMId(tempKey);
//            Map<String, Object> tempChildValues = new HashMap<>();
//            tempChildValues.put("/allIssues/"+tempKey, anIssue.issueToMap());
//            aDatabase.updateChildren(tempChildValues);
//        }
    }

    public static IssueList get(Context c) {
        if (sIssueList == null) {
            sIssueList = new IssueList(c.getApplicationContext());
        }
        return sIssueList;
    }

    public ArrayList<Issue> getIssues() {
//        if (mIssues.size() == 0) {
//            getStartingIssues();
//        }
        return mIssues;
    }

    public Issue getIssue(String id) {
        for (int i = 0; i < mIssues.size(); i++) {
            if (mIssues.get(i).getMId().equals(id)) {
                return mIssues.get(i);
            }
        }
        return null;
    }

    private void getStartingIssues() {
        DatabaseReference aDatabase = FirebaseDatabase.getInstance().getReference();
        aDatabase.child("allIssues").orderByKey().startAt("-L2Hb9o2CzysaBUvp15v").limitToFirst(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot aSnapshot: dataSnapshot.getChildren()) {
                    Issue anIssue = aSnapshot.getValue(Issue.class);
                    mIssues.add(anIssue);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createStartingIssues(Context appContext) {
        Issue tempIssue = new Issue();
        tempIssue.setName("Raised Sidewalk");
        tempIssue.setDescription("This raised sidewalk near Li Ka Shing is a hazard to pedestrians - especially those with impairments. Please help us FixIt!");
        Drawable tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_sidewalk);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.873418, -122.265070); //Location of Li Ka Shing
        tempIssue.setScore(23);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Parking Sign Overlap");
        tempIssue.setDescription("These parking signs are way too confusing to read and follow. There are too many, and they seem to overlap. It would be much nicer to have a single sign with clear instructions that we can follow accordingly. Please help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.confusing_parking_signs);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8682753,-122.2543885); //Location of Haste Street / People's Park
        tempIssue.setScore(112);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Broken Street Lamps");
        tempIssue.setDescription("It's too dark on this street and I feel unsafe walking back home after work at night. It would be awesome if the city could help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_street_lights);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8762932,-122.2589373); //Location of Le Roy and Ridge Road intersection
        tempIssue.setScore(37);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Bathroom Graffiti");
        tempIssue.setDescription("There's a lot of graffiti in the bathroom stalls in Wurster. The stalls seem kinda run-down. Would it be possible to FixIt?");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.graffiti);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8706, -122.2547); // wurster
        tempIssue.setScore(8);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Broken Glass Window");
        tempIssue.setDescription("I'm worried that this broken glass creates an unsafe working environment for employees here! Plus it compromises security. Help us FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.broken_glass);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8692459,-122.2616494); //Amazon store
        tempIssue.setScore(64);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Incomplete Trail Lining");
        tempIssue.setDescription("It seems like there's an incomplete trail being cared out on Memorial Glade. There are some strips of wood maybe meant to line the path? It would be awesome if the University Officials could FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.incomplete_trail_lining);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8733785,-122.2589375); // Memorial Glade!
        tempIssue.setScore(2);
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Ants in Foothill Dining");
        tempIssue.setDescription("There are a lot of ants in the Foothill Dining Commons. Not only is this a concern because there are ants near the food, but it is also concerning to me because I am scared of ants! Please someone FixIt!");
        tempDrawable = appContext.getResources().getDrawable(R.drawable.ants);
        tempIssue.setStringPicWithBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        tempIssue.setLatLon(37.8752884,-122.2557182); // Foothill!
        tempIssue.setScore(89);
        mIssues.add(tempIssue);
    }

}
