package fireAlarmRMIServer;

import java.io.IOException;
import java.util.ArrayList;

import fireAlarm.FireAlarmDesktop;

//This Interface is used to give RMI Clients the access to RMI Server
public interface FireAlarmSensor extends java.rmi.Remote{
	
	public ArrayList<FireAlarmDTO> getFireSensorDetails() throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO addFireAlarm(String token,int roomNo,int floorNo) throws java.rmi.RemoteException , IOException;
	
	public void registerFireAlarmDesktopClient(FireAlarmDesktop fireAlarm) throws java.rmi.RemoteException , IOException;
	
	public void unregisterFireAlarmDesktopClient(FireAlarmDesktop fireAlarm) throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO editFireAlarmDetails(String id,String token,int roomNo,int floorNo) throws java.rmi.RemoteException , IOException;
	
	public FireAlarmDTO deleteFireAlarmDetails(String id,String token) throws java.rmi.RemoteException , IOException;
	
}
