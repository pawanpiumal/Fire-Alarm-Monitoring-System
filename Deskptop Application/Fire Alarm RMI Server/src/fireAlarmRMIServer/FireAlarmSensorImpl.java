package fireAlarmRMIServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import fireAlarm.FireAlarmDesktop;
import fireAlarmRMIServer.FireAlarmDTO;

public class FireAlarmSensorImpl extends UnicastRemoteObject implements FireAlarmSensor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static volatile List<FireAlarmDesktop> fireAlaramClientList = new ArrayList<FireAlarmDesktop>();

	public FireAlarmSensorImpl() throws RemoteException {
	}

	@Override
	public ArrayList<FireAlarmDTO> getFireSensorDetails() throws RemoteException, IOException {
		// getting all fireAlarmDetails
		URL url = new URL("http://localhost:5000/api/firealarm/");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");

		// getting the response status code
		int responseCode = con.getResponseCode();
		// Reading the response
		Reader br = null;
		// checking the response is a success or an error
		if (responseCode >= 200 && responseCode <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}

		// parsing the JSON response to a Java Object
		Gson gson = new Gson();
		try {
			FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
			if (fireAlarmDTO != null) {

				return (ArrayList<FireAlarmDTO>) fireAlarmDTO.getData();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public FireAlarmDTO addFireAlarm(String token, int roomNo, int floorNo) throws RemoteException, IOException {
		// Setting the URL to get the fireAlarmData
		URL url = new URL("http://localhost:5000/api/firealarm/addFireAlarm");
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		con.setRequestMethod("POST");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Authorization", "Bearer " + token);
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + "    \"roomNo\": \"" + roomNo + "\",\r\n" + "    \"floorNo\": \"" + floorNo + "\"\r\n"
				+ "}";
		OutputStream os = con.getOutputStream();
		byte[] input = data.getBytes("utf-8");
		os.write(input, 0, input.length);

		// getting the response status code
		int responseCode = con.getResponseCode();
		// Reading the response
		Reader br = null;
		// checking the response is a success or an error
		if (responseCode >= 200 && responseCode <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}

		// parsing the JSON response to a Java Object
		Gson gson = new Gson();
		FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
		if (fireAlarmDTO != null) {
			return fireAlarmDTO;
		}

		return null;

	}

	@Override
	public void registerFireAlarmDesktopClient(FireAlarmDesktop fireAlarm) throws RemoteException, IOException {
		fireAlaramClientList.add(fireAlarm);
	}

	@Override
	public void unregisterFireAlarmDesktopClient(FireAlarmDesktop fireAlarm) throws RemoteException, IOException {
		fireAlaramClientList.remove(fireAlarm);
	}

	@Override
	public FireAlarmDTO editFireAlarmDetails(String id, String token, int roomNo, int floorNo)
			throws RemoteException, IOException {

		// Setting the URL to get the fireAlarmData
		URL url = new URL("http://localhost:5000/api/firealarm/update/" + id);
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		// Since PATCH is not available in HttpURLConnection this is a workaround
		allowMethods("PATCH");
		con.setRequestMethod("PATCH");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Authorization", "Bearer " + token);
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + "    \"roomNo\": \"" + roomNo + "\",\r\n" + "    \"floorNo\": \"" + floorNo + "\"\r\n"
				+ "}";
		OutputStream os = con.getOutputStream();
		byte[] input = data.getBytes("utf-8");
		os.write(input, 0, input.length);

		// getting the response status code
		int responseCode = con.getResponseCode();
		// Reading the response
		Reader br = null;
		// checking the response is a success or an error
		if (responseCode >= 200 && responseCode <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}

		// parsing the JSON response to a Java Object
		Gson gson = new Gson();
		FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
		if (fireAlarmDTO != null) {
			return fireAlarmDTO;
		}

		return null;
	}

	@Override
	public FireAlarmDTO deleteFireAlarmDetails(String id, String token) throws RemoteException, IOException {
		// Setting the DELETE HTTP Request
		URL url = new URL("http://localhost:5000/api/firealarm/" + id);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("DELETE");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token);
		// getting the response status code
		int responseCode = con.getResponseCode();

		// Reading the response
		Reader br = null;
		// checking the response is a success or an error
		if (responseCode >= 200 && responseCode <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}

		// parsing the JSON response to a Java Object
		Gson gson = new Gson();
		try {
			FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
			if (fireAlarmDTO != null) {
				return fireAlarmDTO;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void checkFireAlarmSensors() throws IOException {
		// getting all fireAlarmDetails
		URL url = new URL("http://localhost:5000/api/firealarm/");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");

		// getting the response status code
		int responseCode = con.getResponseCode();
		// Reading the response
		Reader br = null;
		// checking the response is a success or an error
		if (responseCode >= 200 && responseCode <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}

		// parsing the JSON response to a Java Object
		Gson gson = new Gson();
		try {
			FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
			if (fireAlarmDTO != null) {
				for(FireAlarmDTO fdt:fireAlarmDTO.getData()){
					if(fdt.getSmokeLevel() >5 || fdt.getCo2Level()>5) {
						if(fdt.getStatus().equals("Active")) {
							notifyAllListners(fdt);
						}
					}
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void notifyAllListners(FireAlarmDTO fireDto) {
		for (FireAlarmDesktop fireAlarmDesktop : fireAlaramClientList) {
			try {
				fireAlarmDesktop.showAlert(fireDto);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	// method allowing to use PATCH in HttpURLConnection
	// Source
	// https://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch
	private static void allowMethods(String methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			methodsField.setAccessible(true);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
