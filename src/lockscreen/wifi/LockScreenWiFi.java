package lockscreen.wifi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LockScreenWiFi extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock_screen_wi_fi);
		TextView textView = new TextView(this);
		textView.setText("Forget about the Application. Just place the widget on either homescreen/lockscreen");
	}
}
