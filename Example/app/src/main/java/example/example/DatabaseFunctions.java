package example.example;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by aiflab on 1/7/18.
 */

public class DatabaseFunctions {

    DatabaseFunctions() {

    }

    public static void loadMoreIssues(DatabaseReference aDatabaseRef, final ArrayList<Issue> someIssues, int howManyToLoad) {
        if (aDatabaseRef == null) {
            aDatabaseRef = FirebaseDatabase.getInstance().getReference().child("allIssues");
        }

        String lastId = someIssues.get(someIssues.size()-1).getMId();
        aDatabaseRef.orderByKey().startAt(lastId).limitToFirst(howManyToLoad+1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean firstOne = true;
                for (DataSnapshot aSnapshot: dataSnapshot.getChildren()) {
                    if (firstOne) {
                        firstOne = false;
                        continue;
                    }
                    Issue anIssue = aSnapshot.getValue(Issue.class);
//                    someIssues.add(anIssue);
                    someIssues.add(anIssue);
                    Log.d("debug", "Database: Adding stuff to list!!!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
