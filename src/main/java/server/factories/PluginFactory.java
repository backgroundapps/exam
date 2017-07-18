package server.factories;

import common.Functionality;
import common.FunctionalityImpl;
import server.dao.FunctionalityDAO;
import server.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PluginFactory {

    public static Functionality getFunctionalityByResultSet(ResultSet rst) throws SQLException {
        return new FunctionalityImpl(rst.getLong(UserDAO.FIELDS.ID.name()), rst.getString(FunctionalityDAO.FIELDS.NAME.name()), rst.getString(FunctionalityDAO.FIELDS.DESCRIPTION.name()), rst.getDate(FunctionalityDAO.FIELDS.START_DATE.name()));
    }
}
