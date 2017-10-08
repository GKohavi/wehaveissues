package example.example;

import android.support.v4.app.Fragment;

/**
 * Created by aiflab on 10/6/17.
 */

public class IssueListViewActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new IssueListViewFragment();
    }
}



