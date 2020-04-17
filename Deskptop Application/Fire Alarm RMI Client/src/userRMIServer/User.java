package userRMIServer;

import java.io.IOException;

public interface User extends java.rmi.Remote{
	
	public UserDTO login(UserDTO user) throws java.rmi.RemoteException,IOException;
	
	public UserDTO register(UserDTO user) throws java.rmi.RemoteException,IOException;
	
}
