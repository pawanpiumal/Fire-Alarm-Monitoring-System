package fireAlarmSensor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FireAlarmImpl implements FireAlarm {

	// This method is used to set the fire alarm details for the fire alarm sensor
	// dummy GUI
	// It gets all the fire alarm sensor details first and then select the relevant
	// fire alarm sensor
	// details from them and returns it using a Fire Alarm DTO
	@Override
	public FireAlarmDTO getFireAlarmData(String id) throws IOException {
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
				List<FireAlarmDTO> list = fireAlarmDTO.getData();
				for (FireAlarmDTO iterator : list) {
					if (iterator.getId().equals(id)) {
						return iterator;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// This method is used to set the co2 level and smoke level of a fire alarm
	// sensor
	// it returns the success status and the message
	@Override
	public FireAlarmDTO updateFireAlarmData(String id, int co2Level, int smokeLevel) throws IOException {
		// Setting the REST API URL to PATCH Request
		URL url = new URL("http://localhost:5000/api/firealarm/" + id);
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		// Since PATCH is not available in HttpURLConnection this is a workaround
		allowMethods("PATCH");
		con.setRequestMethod("PATCH");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + "    \"co2Level\":\"" + co2Level + "\",\r\n" + "    \"smokeLevel\": \"" + smokeLevel
				+ "\"\r\n" + "}";
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

	// This method is used to set the status of a fire alarm
	// sensor
	// it returns the success status and the message
	@Override
	public FireAlarmDTO updateFireAlarmData(String id, String status) throws IOException {
		// Setting the REST API URL to PATCH Request
		URL url = new URL("http://localhost:5000/api/firealarm/" + id);
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		// Since PATCH is not available in HttpURLConnection this is a workaround
		allowMethods("PATCH");
		con.setRequestMethod("PATCH");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + "    \"status\":\"" + status + "\"\r\n" + "}";
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
		try {
			FireAlarmDTO fireAlarmDTO = gson.fromJson(br, FireAlarmDTO.class);
			if (fireAlarmDTO != null) {
				return fireAlarmDTO;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	//This method is used to get the all the FireAlarm Ids
	//This fire alarm ids displayed in the combo box
	//Using the id's the user can get sensor details of the fire alarms
	@Override
	public ArrayList<String> getFireAlarmIDs() throws IOException {
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
				List<String> ids = new ArrayList<String>();
				List<FireAlarmDTO> list = fireAlarmDTO.getData();
				for (FireAlarmDTO string : list) {
					ids.add(string.getId());
				}
				return (ArrayList<String>) ids;

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// method allowing to use PATCH in HttpURLConnection
	// Source
	// https://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch
	private static void allowMethods(String... methods) {
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
