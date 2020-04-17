package fireAlarmRMIServer;

import java.io.Serializable;
import java.util.List;

//This Java Object is used to transfer data of fireAlarm 
//between HTTP Requests and GUI
public class FireAlarmDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1620796167638083179L;
	private String _id;
	private String floorNo;
	private String roomNo;
	private String lastUpdated;
	private String status;
	private int co2Level;
	private int smokeLevel;

	private boolean success;
	private String msg;
	private int statusCode;

	private List<FireAlarmDTO> data;
	
	private FireAlarmDTO savedData;

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCo2Level() {
		return co2Level;
	}

	public void setCo2Level(int co2Level) {
		this.co2Level = co2Level;
	}

	public int getSmokeLevel() {
		return smokeLevel;
	}

	public void setSmokeLevel(int smokeLevel) {
		this.smokeLevel = smokeLevel;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<FireAlarmDTO> getData() {
		return data;
	}

	public void setData(List<FireAlarmDTO> data) {
		this.data = data;
	}


	public FireAlarmDTO getSavedData() {
		return savedData;
	}

	public void setSavedData(FireAlarmDTO savedData) {
		this.savedData = savedData;
	}
	
	

}
