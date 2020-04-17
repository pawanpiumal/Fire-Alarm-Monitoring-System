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

			String fireAlarmRegistration = "rmi://" + registry + "/TemperatureSensor";
			String userRegistration = "rmi://" + registry + "/TemperatureSensor";

			Naming.rebind(fireAlarmRegistration, sensor);
			Naming.rebind(userRegistration, user);

			System.out.println("done");

//			for (FireAlarmDTO string : sensor.getFireSensorDetails()) {
//				System.out.println(string.getId());
//			}
			
			
//			System.out.println(sensor.addFireAlarm(
//					"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhRnJvbURCIjp7Il9pZCI6IjVlOTQyZGZhNDNhNDI2M2E1YzhmMWE5MSIsInVzZXJOYW1lIjoiR1BQQSIsInBhc3N3b3JkIjoiMTIzIiwiZW1haWwiOiJncHBhZ3JvdXBAZ21haWwuY29tIiwibW9iaWxlTnVtYmVyIjo3MTI5MTcyNTcsIl9fdiI6MH0sImlhdCI6MTU4NzEyNTAzNywiZXhwIjoxNTg3MTM5NDM3fQ.dTbVtsFEEz_mbLJoKOlD8zrgtrSJB1Uctt01noD_Lq8",
//					2, 3).getMsg());
			

//			System.out.println(sensor.editFireAlarmDetails("5e9358583c53ab22741c9c96",
//					"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhRnJvbURCIjp7Il9pZCI6IjVlOTQyZGZhNDNhNDI2M2E1YzhmMWE5MSIsInVzZXJOYW1lIjoiR1BQQSIsInBhc3N3b3JkIjoiMTIzIiwiZW1haWwiOiJncHBhZ3JvdXBAZ21haWwuY29tIiwibW9iaWxlTnVtYmVyIjo3MTI5MTcyNTcsIl9fdiI6MH0sImlhdCI6MTU4NzEyNTAzNywiZXhwIjoxNTg3MTM5NDM3fQ.dTbVtsFEEz_mbLJoKOlD8zrgtrSJB1Uctt01noD_Lq8",
//					2, 3).getMsg());

//			System.out.println(sensor.deleteFireAlarmDetails("5e9358583c53ab22741c9c96",
//					"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhRnJvbURCIjp7Il9pZCI6IjVlOTQyZGZhNDNhNDI2M2E1YzhmMWE5MSIsInVzZXJOYW1lIjoiR1BQQSIsInBhc3N3b3JkIjoiMTIzIiwiZW1haWwiOiJncHBhZ3JvdXBAZ21haWwuY29tIiwibW9iaWxlTnVtYmVyIjo3MTI5MTcyNTcsIl9fdiI6MH0sImlhdCI6MTU4NzEyNTAzNywiZXhwIjoxNTg3MTM5NDM3fQ.dTbVtsFEEz_mbLJoKOlD8zrgtrSJB1Uctt01noD_Lq8"
//					).getMsg());
			UserDTO usd = new UserDTO();
			usd.setUserName("GPPA3");
//			usd.setEmail("gppagroup3@gmail.com");
			usd.setPassword("123");
//			usd.setMobileNumber("0714123123");
//			
//			if((usd=user.register(usd))!=null) {
//				System.out.println(usd.getToken());
//			}else {
//				System.out.println("not");
//			}
			
			if ((usd = user.login(usd)) != null) {
				System.out.println(usd.getSuccess());
				System.out.println(usd.getMsg());
				System.out.println(usd.getToken());
			} else {
				System.out.println("not");
			}

		} catch (Exception e) {
			System.err.println("Error - " + e);
		}

	}

}
