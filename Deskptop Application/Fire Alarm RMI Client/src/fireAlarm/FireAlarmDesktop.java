package fireAlarm;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fireAlarmRMIServer.FireAlarmDTO;

//this interface is used to set the remote method that RMI Server has access
//to when showing an alert if one of the fire alarm sensors have exceeded the limit
public interface FireAlarmDesktop extends Remote{
	
	
	public void showAlert(FireAlarmDTO fireDto) throws RemoteException;

}
