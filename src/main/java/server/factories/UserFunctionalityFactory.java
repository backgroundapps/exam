package server.factories;

import common.FunctionalityImpl;
import common.UserFunctionality;
import common.UserFunctionalityImpl;
import common.UserImpl;
import server.dao.queries.FunctionalityQueries;
import server.dao.queries.UserFunctionalityPermissionQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFunctionalityFactory {
    public static UserFunctionality getUserFunctionalityByResultSet(ResultSet result) throws SQLException {
        return new UserFunctionalityImpl(
                new UserImpl(result.getLong(UserFunctionalityPermissionQueries.FIELDS.USER_ID.name())),
                new FunctionalityImpl(result.getLong(UserFunctionalityPermissionQueries.FIELDS.FUNCTIONALITY_ID.name()))

        );
    }
}
