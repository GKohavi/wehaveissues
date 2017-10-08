package example.example;

import android.content.Context;

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
        tempIssue.setName("First Issue!");
        tempIssue.setDescription("Broken light bulb");
        mIssues.add(tempIssue);

        tempIssue = new Issue();
        tempIssue.setName("Second Issue!");
        tempIssue.setDescription("Graffiti");
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
