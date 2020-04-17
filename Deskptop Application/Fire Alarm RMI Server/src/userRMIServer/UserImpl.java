package userRMIServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.google.gson.Gson;

public class UserImpl extends UnicastRemoteObject implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserImpl() throws RemoteException {

	}

	@Override
	public UserDTO register(UserDTO user) throws RemoteException, IOException {
		// Setting the URL to get the fireAlarmData
		URL url = new URL("http://localhost:5000/api/users/signup");
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		con.setRequestMethod("POST");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + "    \"userName\": \"" + user.getUserName() + "\",\r\n" + "    \"password\": \""
				+ user.getPassword() + "\",\r\n" + "    \"email\": \"" + user.getEmail() + "\",\r\n"
				+ "    \"mobileNumber\": \"" + user.getMobileNumber() + "\"\r\n" + "}";
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
		UserDTO userDTO = gson.fromJson(br, UserDTO.class);
		if (userDTO != null) {
			return userDTO;
		}

		return null;
	}

	@Override
	public UserDTO login(UserDTO user) throws RemoteException, IOException {
		// Setting the URL to get the fireAlarmData
		URL url = new URL("http://localhost:5000/api/users/login");
		// Opening a Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// Setting the Request Method
		con.setRequestMethod("POST");
		// Setting the Request Content Type
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		// Setting the Request header to accept response in JSON Format
		con.setRequestProperty("Accept", "application/json");
		// Enabling the writing to the connection output stream
		con.setDoOutput(true);
		// Creating the Data in the request body and writing it to output stream
		String data = "{\r\n" + 
				"    \"userName\": \""+user.getUserName()+"\",\r\n" + 
				"    \"password\": \""+user.getPassword()+"\"\r\n" + 
				"}";
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
		UserDTO userDTO = gson.fromJson(br, UserDTO.class);
		if (userDTO != null) {
			return userDTO;
		}

		return null;
	}

}
