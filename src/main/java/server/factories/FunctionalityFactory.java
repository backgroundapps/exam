package server.factories;

import common.Functionality;
import common.FunctionalityImpl;
import common.PluginImpl;
import server.dao.queries.FunctionalityQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionalityFactory {

    public static Functionality getFunctionalityByResultSet(ResultSet rst) throws SQLException {
        return new FunctionalityImpl(
                rst.getLong(FunctionalityQueries.FIELDS.ID.name()),
                rst.getString(FunctionalityQueries.FIELDS.NAME.name()),
                rst.getString(FunctionalityQueries.FIELDS.DESCRIPTION.name()),
                rst.getDate(FunctionalityQueries.FIELDS.START_DATE.name()),
                new PluginImpl(rst.getLong(FunctionalityQueries.FIELDS.PLUGIN_ID.name()))

        );
    }
}
