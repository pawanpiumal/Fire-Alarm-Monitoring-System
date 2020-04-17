package fireAlarmRMIServerMain;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import fireAlarmRMIServer.FireAlarmSensor;
import fireAlarmRMIServer.FireAlarmSensorImpl;
import userRMIServer.User;
import userRMIServer.UserDTO;
import userRMIServer.UserImpl;

public class RMIServerMain {

	public static void main(String[] args) {

		System.setProperty("java.security.policy", "file:allowall.policy");
		System.out.println("Loading Fire Alarm  service");

		try {
			FireAlarmSensor sensor = new FireAlarmSensorImpl();
			User user = new UserImpl();
			
			int PORT =3000;
			LocateRegistry.createRegistry(PORT);
			String registry = "localhost:"+PORT;

			String fireAlarmRegistration = "rmi://" + registry + "/FireAlarm";
			String userRegistration = "rmi://" + registry + "/User";

			Naming.rebind(fireAlarmRegistration, sensor);
			Naming.rebind(userRegistration, user);

			System.out.println("RMI Server Running");

		} catch (Exception e) {
			System.err.println("Error - " + e);
		}

	}

}
