package userRMIServer;

import java.io.IOException;

//RMI Clients use this Interface to access RMI Servers method to login and register clients

public interface User extends java.rmi.Remote{
	
	public UserDTO login(UserDTO user) throws java.rmi.RemoteException,IOException;
	
	public UserDTO register(UserDTO user) throws java.rmi.RemoteException,IOException;
	
}
