package lockscreen.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class WidgetIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		 
		if (intent.getAction().equals(WidgetProvider.On_Off_Action)) {
			 if (WidgetProvider.getWiFiState()==true) {
		    		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		    	    wifiManager.setWifiEnabled(false);
		    	}
		    	else {
		    		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		    	    wifiManager.setWifiEnabled(true);
		    	}
		 }
		
		else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
			int tWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);
			WidgetProvider.setCorrectImage(tWifiState, context);
			
		}
		 
		 
	}
}
