package unit;


import common.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.dao.FunctionalityDAO;
import server.dao.PluginDAO;
import server.dao.UserDAO;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;
import server.dao.utils.UserFunctionalityPermissionDAO;

import static org.junit.Assert.*;

import java.sql.SQLException;


public class UserFunctionalityPermissionDAOTest {
    private UserDAO userDAO;
    private FunctionalityDAO functionalityDAO;
    private UserFunctionalityPermissionDAO dao;

    private PluginImpl plugin;
    private User user;

    private StatementDMLBuilder dml;
    private StatementDDLBuilder ddl;

    private void persistPlugin() throws SQLException {
        plugin = new PluginImpl(1L, "PLUGIN", "PLUGINBUILDER");
        new PluginDAO(ddl, dml).create(plugin );
    }
    
    private void addFuncinalities() throws SQLException {
        String[] functionalitiesName = {"CREATE", "READ", "UPDATE", "REMOVE"};
        for (int i = 0; i < functionalitiesName.length; i++) {
            functionalityDAO.create(new FunctionalityImpl(functionalitiesName[i], functionalitiesName[i], plugin));
        }
    }

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        ddl = StatementBuilderFactory.getDDLBuilderInstance();
        dml = StatementBuilderFactory.getDMLBuilderInstance();

        userDAO = new UserDAO(ddl, dml);
        functionalityDAO = new FunctionalityDAO(ddl, dml);
        dao = new UserFunctionalityPermissionDAO(ddl, dml);

        user = new UserImpl(1L, "ARIOSVALDO", "ARIOSVALDO LENNON", "ACTIVE", "Y");
        userDAO.create(user);

        persistPlugin();
        addFuncinalities();

        Functionality functionality = functionalityDAO.findByName("CREATE");
        assertTrue(dao.addPermission(user, functionality));


    }


    @Test
    public void shouldReturnNumberOfUserFunctionalities() throws SQLException {
        assertNotNull(dao.numberOfUserFunctionalities());
    }

    @Test
    public void shouldReturnSomeUserFunctionality() throws SQLException {
        assertFalse(dao.listUserFunctionalities().isEmpty());
        assertNotNull(dao.listUserFunctionalities().get(0).getUser());
        assertNotNull(dao.listUserFunctionalities().get(0).getFunctionality());
    }

    @Test
    public void shouldReturnSomeUserFromFunctionality() throws SQLException {
        Functionality functionality = functionalityDAO.findByName("CREATE");
        assertEquals(user.getId(), dao.findUsersFromFunctionality(functionality).get(0).getUser().getId());
    }


    @Test
    public void shouldReturnSomeFunctionalitiesByUser() throws SQLException {
        assertNotNull(dao.findFunctionalitiesByUser(user).get(0).getFunctionality().getId());
    }

    @Test
    public void shouldAddCREATEPermission() throws SQLException {
        Functionality functionality = functionalityDAO.findByName("READ");
        assertTrue(dao.addPermission(user, functionality));
    }


    @After
    public void deleteAllElements() throws SQLException {
        dao.deleteAllElements();
        functionalityDAO.deleteAllElements();
        userDAO.deleteAllElements();
        new PluginDAO(ddl, dml).deleteAllElements();

    }


}
