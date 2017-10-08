package example.example;

import android.support.v4.app.Fragment;

/**
 * Created by aiflab on 10/7/17.
 */

public class CreateIssueActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CreateIssueFragment();
    }
}
