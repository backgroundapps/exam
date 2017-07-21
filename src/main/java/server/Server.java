package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import server.process.FunctionalityProcess;
import server.process.PermissionProcess;
import server.process.PluginProcess;
import server.process.UserProcess;

  public class Server implements ServerInterface {

  public static void main(String args[]) {
    try {
      ServerInterface server = new Server();
      UnicastRemoteObject.exportObject(server, ServerInterface.RMI_PORT);

      Registry registry = LocateRegistry.createRegistry(ServerInterface.RMI_PORT);
      registry.rebind(ServerInterface.REFERENCE_NAME, server);

      System.out.println("Server ready");
    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }

  /*



  */
  @Override
  public List<User> getUser() throws RemoteException, SQLException {
    try {
      return new UserProcess().getUsers();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Long getNumberOfUsers() throws RemoteException, SQLException {
    return new UserProcess().getNumberOfUsers();
  }

  @Override
  public List<Functionality> getFunctionalities() throws RemoteException, SQLException {
    return new FunctionalityProcess().getFunctionalities();
  }

  @Override
  public List<Plugin> getPlugins() throws RemoteException, SQLException {
    return new PluginProcess().getPlugins();
  }

  @Override
  public List<UserFunctionality> getPermissions() throws RemoteException, SQLException {
    return new PermissionProcess().getPermissions();
  }


}