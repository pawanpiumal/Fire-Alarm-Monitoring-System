package fireAlarmSensor;

import java.io.IOException;
import java.util.ArrayList;

public interface FireAlarm {
	
	public ArrayList<String> getFireAlarmIDs() throws IOException;
	
	public FireAlarmDTO getFireAlarmData(String id) throws IOException;
	
	public FireAlarmDTO updateFireAlarmData(String id,int co2Level,int smokeLevel) throws IOException;

	public FireAlarmDTO updateFireAlarmData(String id,String status) throws IOException;
	
}
