package unit;


import common.User;
import common.UserImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.dao.FunctionalityDAO;
import server.dao.UserDAO;

import java.sql.SQLException;


public class UserFunctionalityPermissionDAOTest {
    private UserDAO userDAO;
    private FunctionalityDAO functionalityDAO;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        userDAO = new UserDAO();
        functionalityDAO = new FunctionalityDAO();
        userDAO.create(new UserImpl("ARIOSVALDO", "ARIOSVALDO LENNON", "ACTIVE", "Y"));
    }


    @After
    public void deleteAllElements() throws SQLException {
    }


}
