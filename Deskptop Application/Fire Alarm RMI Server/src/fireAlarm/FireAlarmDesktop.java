package fireAlarm;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fireAlarmRMIServer.FireAlarmDTO;

//This is the RMI Clients interface which enables the RMI Server to send
//alerts to the RMI Client desktop application
public interface FireAlarmDesktop extends Remote{
	
	public void showAlert(FireAlarmDTO fireDto) throws RemoteException;

}
