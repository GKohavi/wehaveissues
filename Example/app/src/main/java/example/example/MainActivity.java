package example.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.viewButton)
    void openViewActivity(){
//        Toast.makeText(getApplication(), "HERERERE", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,IssueListViewActivity.class));
    }

    @OnClick(R.id.addIssueButton)
    void openAddIssueActivity(){
        Toast.makeText(getApplication(), "Going to Add Issue Button", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,CreateIssueActivity.class));
    }

}
