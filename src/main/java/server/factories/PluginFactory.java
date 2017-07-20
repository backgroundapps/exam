package server.factories;

import common.Plugin;
import common.PluginImpl;
import server.dao.queries.PluginQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PluginFactory {

    public static Plugin getPluginByResultSet(ResultSet rst) throws SQLException {
        return new PluginImpl(
                rst.getLong(PluginQueries.FIELDS.ID.name()),
                rst.getString(PluginQueries.FIELDS.NAME.name()),
                rst.getString(PluginQueries.FIELDS.DESCRIPTION.name()),
                rst.getDate(PluginQueries.FIELDS.START_DATE.name())
        );
    }
}
