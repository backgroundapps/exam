package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
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
  public List<User> getUsers() throws RemoteException, SQLException {
    try {
      return new UserProcess().getUsers();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User findUsersById(Long id) throws RemoteException, SQLException {
    try {
      return new UserProcess().findById(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


    @Override
  public Boolean isValidLogin(String login) throws RemoteException, SQLException {
    try {
      return new UserProcess().isValidLogin(login);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
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
  public Plugin getPluginByName(String name) throws RemoteException, SQLException {
    return new PluginProcess().findByName(name);
  }

  @Override
  public Functionality getFunctionalityByName(String name) throws RemoteException, SQLException {
    return new FunctionalityProcess().findByName(name);
  }

  @Override
  public String[] getPluginMappedNames() throws RemoteException, SQLException {
    return new PluginProcess().getMappedNames();
  }

    @Override
    public String[] getFunctionalityMappedNames() throws RemoteException, SQLException {
      return new FunctionalityProcess().getMappedNames();
    }


    @Override
  public List<UserFunctionality> getPermissions() throws RemoteException, SQLException {
    return new PermissionProcess().getPermissions();
  }



  }