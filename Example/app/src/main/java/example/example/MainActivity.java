package example.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button mViewIssuesButton;
    private Button mMapViewButton;
    private Button mAddIssueButton;
    private Button mUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewIssuesButton = (Button)findViewById(R.id.viewButton);
        mViewIssuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewIssuesClicked();
            }
        });

        mMapViewButton = (Button)findViewById(R.id.mapViewButton);
        mMapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapViewClicked();
            }
        });

        mAddIssueButton = (Button)findViewById(R.id.addIssueButton);
        mAddIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIssueClicked();
            }
        });

        mUserProfile = (Button)findViewById(R.id.userProfileButton);
        mUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfileClicked();
            }
        });


    }

    private void viewIssuesClicked() {
        startActivity(new Intent(MainActivity.this,IssueListViewActivity.class));
    }

    private void mapViewClicked() {
        startActivity(new Intent(MainActivity.this,MapsActivity.class));
    }

    private void addIssueClicked() {
        startActivity(new Intent(MainActivity.this,CreateIssueActivity.class));
    }

    private void userProfileClicked() {
        startActivity(new Intent(MainActivity.this,UserProfileActivity.class));
    }
}
