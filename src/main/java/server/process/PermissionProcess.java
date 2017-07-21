package server.process;

import common.User;
import server.dao.UserDAO;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;

import java.sql.SQLException;
import java.util.List;

public class PermissionProcess {
    public List<User> listUsers() throws SQLException {
            StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).listUsers();

        }finally {
            ddl.close();
        }
    }

}
