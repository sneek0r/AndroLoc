package my.androloc;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.TextView;

public class Main extends Activity implements LocationListener {
	
	public LocationManager manager;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	
    	// get system location manager
    	this.manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    	
    	Criteria criteria = new Criteria();
    	criteria.setAccuracy(Criteria.ACCURACY_FINE);
    	criteria.setPowerRequirement(Criteria.POWER_HIGH);
    	// Indicates whether the provider is allowed to incur monetary cost. 
    	criteria.setCostAllowed(false);
    	criteria.setAltitudeRequired(true);
    	criteria.setBearingRequired(true);
    	criteria.setSpeedRequired(true);
    	
    	// get provider described by criteria
    	String provider = this.manager.getBestProvider(criteria, true);
    	
    	// request location updates
    	this.manager.requestLocationUpdates(provider, 60000, 60, this);
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	// remove location updates
    	this.manager.removeUpdates(this);
    }

	public void onLocationChanged(Location location) {
		
		float accuracy = location.getAccuracy();
		double altitude = location.getAltitude();
		// Returns the direction of travel in degrees East of true North
		float bearing = location.getBearing();
		double latitude = location.getLatitude();
		double longtitude = location.getLongitude();
		float speed = location.getSpeed();
		long time = location.getTime();
		
		TextView accuracy_tv = (TextView)findViewById(R.id.accuracy);
		accuracy_tv.setText(String.format("%.0fm", accuracy));
		accuracy_tv.postInvalidate();
		
		TextView altitude_tv = (TextView)findViewById(R.id.altitude);
		altitude_tv.setText(Double.toString(altitude));
		altitude_tv.postInvalidate();
		
		TextView bearing_tv = (TextView)findViewById(R.id.bearing);
		bearing_tv.setText(String.format("%.0f°", bearing));
		bearing_tv.postInvalidate();

		TextView latitude_tv = (TextView)findViewById(R.id.latitude);
		latitude_tv.setText(Location.convert(latitude, Location.FORMAT_DEGREES));
		latitude_tv.postInvalidate();

		TextView longtitude_tv = (TextView)findViewById(R.id.longtitude);
		longtitude_tv.setText(Location.convert(longtitude, Location.FORMAT_DEGREES));
		longtitude_tv.postInvalidate();

		TextView speed_tv = (TextView)findViewById(R.id.speed);
		speed_tv.setText(String.format("%.0fm/s", speed));
		speed_tv.postInvalidate();
		
		TextView time_tv = (TextView)findViewById(R.id.time);
		Time t = new Time(); t.set(time);
		time_tv.setText(DateFormat.format("kk:mm.ss", time));
		time_tv.postInvalidate();
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}