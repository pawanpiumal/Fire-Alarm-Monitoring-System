package fireAlarmRMIServerMain;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import fireAlarmRMIServer.FireAlarmSensor;
import fireAlarmRMIServer.FireAlarmSensorImpl;
import userRMIServer.User;
import userRMIServer.UserImpl;

//THis is the main method of RMI Server

public class RMIServerMain {

	public static void main(String[] args) {
		
		//Setting the security policy
		System.setProperty("java.security.policy", "file:allowall.policy");
		System.out.println("Loading Fire Alarm  service");

		try {
			FireAlarmSensor sensor = new FireAlarmSensorImpl();
			User user = new UserImpl();

			//Registering In RMI registry 
			int PORT = 3000;
			LocateRegistry.createRegistry(PORT);
			String registry = "localhost:" + PORT;

			String fireAlarmRegistration = "rmi://" + registry + "/FireAlarm";
			String userRegistration = "rmi://" + registry + "/User";

			Naming.rebind(fireAlarmRegistration, sensor);
			Naming.rebind(userRegistration, user);

			System.out.println("RMI Server Running");
			//Used to check the fire alarm sensor details in intervals
			int time = 5000;
			checkFireAlarms(time);
			System.out.println("Checking for Fire Alarm CO2, Smoke Level Exceeds in Every " + time + "ms");
		} catch (Exception e) {
			System.err.println("Error - " + e);
		}

	}
	private static FireAlarmSensorImpl sensor ;
	public static void checkFireAlarms(int time) {
		try {
			sensor = new FireAlarmSensorImpl();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {

				for (;;) {
					try {
						sensor.checkFireAlarmSensors();
						Thread.sleep(time);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});

		th.start();
	}

}
