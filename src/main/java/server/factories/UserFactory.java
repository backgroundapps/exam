package server.factories;

import common.User;
import common.UserImpl;
import server.dao.queries.UserQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory {

    public static User getUserByResultSet(ResultSet rst) throws SQLException {
        return new UserImpl(
                rst.getLong(UserQueries.FIELDS.ID.name()),
                rst.getString(UserQueries.FIELDS.LOGIN.name()),
                rst.getString(UserQueries.FIELDS.FULL_NAME.name()),
                rst.getString(UserQueries.FIELDS.STATUS.name()),
                rst.getString(UserQueries.FIELDS.CURRENT_MANAGEMENT.name())
        );
    }
}
