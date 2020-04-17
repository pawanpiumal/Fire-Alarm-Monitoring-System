package fireAlarmRMIServer;

import java.io.IOException;
import java.util.ArrayList;

public interface FireAlarmSensor extends java.rmi.Remote{
	
	public ArrayList<FireAlarmDTO> getFireSensorDetails() throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO addFireAlarm(String token,int roomNo,int floorNo) throws java.rmi.RemoteException , IOException;
	
	public void registerFireAlarmDesktopClient() throws java.rmi.RemoteException , IOException;
	
	public void unregisterFireAlarmDesktopClient() throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO editFireAlarmDetails(String id,String token,int roomNo,int floorNo) throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO deleteFireAlarmDetails(String id,String token) throws java.rmi.RemoteException , IOException;
	
}
