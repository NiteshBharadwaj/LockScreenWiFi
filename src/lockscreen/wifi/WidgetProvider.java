package lockscreen.wifi;


import android.net.wifi.WifiManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class WidgetProvider extends AppWidgetProvider {
	public static final String On_Off_Action = "LOCKSCREEN.WIFI.ACTION.ON_OFF";
	public static boolean WiFiState = false;
	
	public static PendingIntent OnOffPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(On_Off_Action);
	    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}
	public static void UpdateWidget(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, WidgetProvider.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(myWidget, remoteViews);		
	}
	
	public static void setWiFiState(boolean bool) {
		WiFiState = bool;
	}
	public static boolean getWiFiState() {
		return WiFiState;
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	      int[] appWidgetIds) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        views.setOnClickPendingIntent(R.id.on_off_button, OnOffPendingIntent(context));
        views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_unknown);
        WifiManager myWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int tWifiState = myWifiManager.getWifiState();
        setCorrectImage(tWifiState,context);
	}
	
	public static void setCorrectImage(int tWifiState, Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.appwidget);
		switch(tWifiState)  {
			case WifiManager.WIFI_STATE_DISABLED:
				views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_off);
				WidgetProvider.setWiFiState(false);
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_unknown);
				break;
			case WifiManager.WIFI_STATE_ENABLED:
				WidgetProvider.setWiFiState(true);
				views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_on);
				break;
			case WifiManager.WIFI_STATE_ENABLING:
				views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_unknown);
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_unknown);
				break;
	}
	views.setOnClickPendingIntent(R.id.on_off_button, WidgetProvider.OnOffPendingIntent(context));
	WidgetProvider.UpdateWidget(context.getApplicationContext(), views);
	}
	
	

	public void onEnabled(Context context, AppWidgetManager appWidgetManager,
	      int[] appWidgetIds) {

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        views.setOnClickPendingIntent(R.id.on_off_button, OnOffPendingIntent(context));
        views.setImageViewResource(R.id.on_off_button,R.drawable.network_wireless_unknown);
        WifiManager myWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int tWifiState = myWifiManager.getWifiState();
        setCorrectImage(tWifiState,context);
	}
}
