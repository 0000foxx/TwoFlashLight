
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tylerfoxx.twoflash.MainActivity;

public class UnitTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private MainActivity mMainActivity;
    
    public UnitTest(){
        super(MainActivity.class);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        setActivityInitialTouchMode(true);
        mMainActivity = getActivity();
    }
    
    public void testMainActivityNotNull(){
        assertNotNull(mMainActivity);
    }


}
