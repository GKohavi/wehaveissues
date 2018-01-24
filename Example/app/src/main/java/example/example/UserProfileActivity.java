package example.example;

import android.support.v4.app.Fragment;

/**
 * Created by aiflab on 1/9/18.
 */

public class UserProfileActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserProfileFragment();
    }
}
