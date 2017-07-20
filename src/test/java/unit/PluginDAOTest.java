package unit;


import common.Plugin;
import common.PluginImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.dao.PluginDAO;

import java.sql.SQLException;
import java.util.Date;


public class PluginDAOTest {
    private PluginDAO pluginDAO;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName ("oracle.jdbc.OracleDriver");
        pluginDAO = new PluginDAO();
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
    }

    @Test
    public void shouldReturnNumberOfPlugins() throws SQLException {
        Assert.assertNotNull(pluginDAO.numberOfPlugins());
    }

    @Test
    public void shouldReturnSomePlugin() throws SQLException {
        Assert.assertFalse(pluginDAO.listPlugins().isEmpty());
        Assert.assertNotNull(pluginDAO.listPlugins().get(0).getName());
    }

    @Test
    public void shouldReturnLastPluginID() throws SQLException {
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
        pluginDAO.create(new PluginImpl("STATUS BAR", "STATUS BAR BUILDER", new Date()));

        Plugin plugin = pluginDAO.lastPlugin();
        Assert.assertEquals(plugin.getId(), pluginDAO.lastId());
    }

    @Test
    public void shouldReturnLastPlugin() throws SQLException {
        pluginDAO.create(new PluginImpl("BUTTON", "BUTTON BUILDER", new Date()));
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));

        Assert.assertEquals("FRAME", pluginDAO.lastPlugin().getName());
    }

    @Test
    public void shouldReturnPluginById_1() throws SQLException {
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
        Long id = pluginDAO.lastId();
        Assert.assertEquals("FRAME", pluginDAO.findById(id).getName());
    }

    @Test
    public void shouldReturnNextId() throws SQLException {
        Assert.assertEquals(new Long(pluginDAO.lastId() + 1), pluginDAO.nextId());
    }

    @Test
    public void shouldAddOnePlugin() throws SQLException {
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
        Assert.assertEquals("FRAME", pluginDAO.lastPlugin().getName());
    }

    @Test
    public void shouldUpdateOnePlugin() throws SQLException {
        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
        Assert.assertEquals("FRAME", pluginDAO.lastPlugins().getName());

        pluginDAO.update(new PluginImpl(null, "FRAME BUILDER FLEX", new Date()), pluginDAO.lastId());

        Plugin plugin = pluginDAO.findById(pluginDAO.lastId());

        Assert.assertEquals("FRAME", plugin.getName());
        Assert.assertEquals("FRAME BUILDER FLEX", plugin.getDescription());
    }



    @Test
    public void shouldRemoveOnePlugin() throws SQLException {
        Plugin plugin = pluginDAO.lastPlugin();

        pluginDAO.create(new PluginImpl("FRAME", "FRAME BUILDER", new Date()));
        Assert.assertEquals("FRAME", pluginDAO.lastPlugin().getName());

        pluginDAO.delete(pluginDAO.lastId());
        Assert.assertEquals(plugin.getName(), pluginDAO.lastPlugin().getName());
    }

    @After
    public void removePlugins() throws  SQLException{
        pluginDAO.deleteAllElements();
        Assert.assertTrue(pluginDAO.listPlugins().isEmpty());
    }


}
