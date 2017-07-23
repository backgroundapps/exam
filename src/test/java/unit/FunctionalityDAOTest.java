package unit;


import common.Functionality;
import common.FunctionalityImpl;
import common.Plugin;
import common.PluginImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.dao.FunctionalityDAO;
import server.dao.PluginDAO;
import server.dao.conf.DB;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;

import java.sql.SQLException;
import java.util.Date;


public class FunctionalityDAOTest {
    private FunctionalityDAO functionalityDAO;
    private Plugin plugin;

    private StatementDDLBuilder ddl;
    private StatementDMLBuilder dml;


    private void persistPlugin() throws SQLException {
        plugin = new PluginImpl(1L, "PLUGIN MOCK", "PLUGIN MOCK");
        new PluginDAO(ddl, dml).create(plugin);
    }

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        ddl = StatementBuilderFactory.getDDLBuilderInstance();
        dml = StatementBuilderFactory.getDMLBuilderInstance();

        persistPlugin();
        functionalityDAO = new FunctionalityDAO(ddl, dml);
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date(), plugin.getId()));
    }

    @Test
    public void shouldReturnNumberOfFunctionalities() throws SQLException {
        Assert.assertNotNull(functionalityDAO.numberOfFunctionalities());
    }

    @Test
    public void shouldReturnSomeFunctionality() throws SQLException {
        Assert.assertFalse(functionalityDAO.listFunctionalities().isEmpty());
        Assert.assertNotNull(functionalityDAO.listFunctionalities().get(0).getName());

    }

    @Test
    public void shouldReturnLastFunctionalityID() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 1", "CREATE MAP", new Date(), 1L));
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 2", "CREATE MAP", new Date(), 1L));

        Functionality functionality = functionalityDAO.lastFunctionality();
        Assert.assertEquals(functionality.getId(), functionalityDAO.lastId());

    }

    @Test
    public void shouldReturnLastFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 3", "CREATE MAP", new Date(), 1L));
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 4", "CREATE MAP", new Date(), 1L));

        Assert.assertEquals("CREATE MAP 4", functionalityDAO.lastFunctionality().getName());

    }

    @Test
    public void shouldReturnFunctionalityById() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 5", "CREATE MAP", new Date(), 1L));
        Long id = functionalityDAO.lastId();
        Assert.assertEquals("CREATE MAP 5", functionalityDAO.findById(id).getName());

    }

    @Test
    public void shouldReturnFunctionalityByName() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP NAME", "CREATE MAP", new Date(),1L));
        Assert.assertEquals("CREATE MAP NAME", functionalityDAO.findByName("CREATE MAP NAME").getName());

    }

    @Test
    public void shouldReturnNextId() throws SQLException {
        Assert.assertEquals(new Long(functionalityDAO.lastId() + 1), functionalityDAO.nextId());
    }

    @Test
    public void shouldAddOneFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 6", "CREATE MAP", new Date(), 1L));
        Assert.assertEquals("CREATE MAP 6", functionalityDAO.lastFunctionality().getName());

    }

    @Test
    public void shouldUpdateOneFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 7.7", "CREATE MAP", new Date(), 1L));
        Assert.assertEquals("CREATE MAP 7.7", functionalityDAO.lastFunctionality().getName());

        Assert.assertTrue(functionalityDAO.update(new FunctionalityImpl(null, "CREATE MAP 7.00", new Date(), 1L), functionalityDAO.lastId()));

        Functionality functionality = functionalityDAO.findById(functionalityDAO.lastId());

        Assert.assertEquals("CREATE MAP 7.7", functionality.getName());
        Assert.assertEquals("CREATE MAP 7.00", functionality.getDescription());

    }

    @Test
    public void shouldRemoveOneFunctionality() throws SQLException {
        Functionality functionality = functionalityDAO.lastFunctionality();

        functionalityDAO.create(new FunctionalityImpl("CREATE MAP 8", "CREATE MAP", new Date(), 1L));
        Assert.assertEquals("CREATE MAP 8", functionalityDAO.lastFunctionality().getName());

        functionalityDAO.delete(functionalityDAO.lastId());
        Assert.assertEquals(functionality.getName(), functionalityDAO.lastFunctionality().getName());
    }

    @After
    public void removePlugins() throws  SQLException{
        DB.restart(ddl, dml);
    }
}
