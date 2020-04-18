package fireAlarm;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fireAlarmRMIServer.FireAlarmDTO;


public interface FireAlarmDesktop extends Remote{
	
	public void showAlert(FireAlarmDTO fireDto) throws RemoteException;

}
