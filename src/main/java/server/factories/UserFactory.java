package server.factories;

import common.User;
import common.UserImpl;
import server.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory {

    public static User getUserByResultSet(ResultSet rst) throws SQLException {
        return new UserImpl(rst.getLong(UserDAO.FIELDS.ID.name()), rst.getString(UserDAO.FIELDS.LOGIN.name()), rst.getString(UserDAO.FIELDS.FULL_NAME.name()), rst.getString(UserDAO.FIELDS.STATUS.name()), rst.getString(UserDAO.FIELDS.CURRENT_MANAGEMENT.name()));
    }
}
