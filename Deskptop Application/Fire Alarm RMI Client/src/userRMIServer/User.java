package userRMIServer;

import java.io.IOException;

//This Interface is RMI Server's User interface
//RMI Client use this interface to access remote methods in RMI Server

public interface User extends java.rmi.Remote{
	
	public UserDTO login(UserDTO user) throws java.rmi.RemoteException,IOException;
	
	public UserDTO register(UserDTO user) throws java.rmi.RemoteException,IOException;
	
}
