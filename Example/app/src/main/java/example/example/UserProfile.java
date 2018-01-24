package example.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by aiflab on 1/9/18.
 */

public class UserProfile {
    private String internalUserId;
    private String externalName;
    private String firstName;
    private String lastName;
    private Date joinDate;
    private HashMap<String, Date> issueIdToDate;
    private ArrayList<String> issuesCreated;

    UserProfile() {
        internalUserId = "";
        firstName = "";
        lastName = "";
        joinDate = new Date();
        issueIdToDate = new HashMap<>();
    }

    public String getInternalUserId() {
        return internalUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public HashMap<String, Date> getIssueIdToDate() {
        return issueIdToDate;
    }



    public void setInternalUserId(String userId) {
        this.internalUserId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setIssueIdToDate(HashMap<String, Date> issueIdToDate) {
        this.issueIdToDate = issueIdToDate;
    }
}
