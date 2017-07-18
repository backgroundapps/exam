package unit;


import common.Functionality;
import common.FunctionalityImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.dao.FunctionalityDAO;

import java.sql.SQLException;
import java.util.Date;


public class FunctionalityDAOTest {
    private FunctionalityDAO functionalityDAO;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName ("oracle.jdbc.OracleDriver");
        functionalityDAO = new FunctionalityDAO();
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
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
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));

        Functionality functionality = functionalityDAO.lastFunctionality();
        Assert.assertEquals(functionality.getId(), functionalityDAO.lastId());

    }

    @Test
    public void shouldReturnLastFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));

        Assert.assertEquals("CREATE MAP", functionalityDAO.lastFunctionality().getName());

    }

    @Test
    public void shouldReturnFunctionalityById_1() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        Long id = functionalityDAO.lastId();
        Assert.assertEquals("CREATE MAP", functionalityDAO.findById(id).getName());

    }

    @Test
    public void shouldReturnNextId() throws SQLException {
        Assert.assertEquals(new Long(functionalityDAO.lastId() + 1), functionalityDAO.nextxId());
    }

    @Test
    public void shouldAddOneFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        Assert.assertEquals("CREATE MAP", functionalityDAO.lastFunctionality().getName());

    }

    @Test
    public void shouldUpdateOneFunctionality() throws SQLException {
        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        Assert.assertEquals("CREATE MAP", functionalityDAO.lastFunctionality().getName());

        functionalityDAO.update(new FunctionalityImpl(null, "CREATE MAP", new Date()), functionalityDAO.lastId());

        Functionality functionality = functionalityDAO.findById(functionalityDAO.lastId());

        Assert.assertEquals("CREATE MAP", functionality.getName());
        Assert.assertEquals("CREATE MAP", functionality.getDescription());
        Assert.assertEquals(new Date(), functionality.getStartDate());

    }

    @Test
    public void shouldRemoveOneFunctionality() throws SQLException {
        Functionality functionality = functionalityDAO.lastFunctionality();

        functionalityDAO.create(new FunctionalityImpl("CREATE MAP", "CREATE MAP", new Date()));
        Assert.assertEquals("CREATE MAP", functionalityDAO.lastFunctionality().getName());

        functionalityDAO.delete(functionalityDAO.lastId());
        Assert.assertEquals(functionality.getName(), functionalityDAO.lastFunctionality().getName());
    }


}
