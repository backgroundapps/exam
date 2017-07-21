package server.dao;

import common.Plugin;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;
import server.dao.utils.Statementable;
import server.factories.PluginFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.PluginQueries.*;

public class PluginDAO {
    private StatementDDLBuilder ddl;
    private StatementDMLBuilder dml;

    public PluginDAO() {
    }

    public PluginDAO(StatementDDLBuilder ddl) {
        this.ddl = ddl;
    }



    public PluginDAO(StatementDDLBuilder ddl, StatementDMLBuilder dml) {
        this.ddl = ddl;
        this.dml = dml;
    }

    public Long numberOfPlugins() throws SQLException {
        Long id = null;

        ddl.addSQL(countId()).build();

        while(ddl.getResult().next()){
            id = ddl.getResult().getLong(1);
        }
        return id;
    }

    public List<Plugin> listPlugins() throws SQLException {

        List<Plugin> list = new ArrayList<>();
        ddl.addSQL(selectAll()).build();
        while(ddl.getResult().next()){
            list.add(PluginFactory.getPluginByResultSet(ddl.getResult()));
        }
        return list;
    }

    public Plugin lastPlugin() throws SQLException {
        Plugin plugin = null;

        Long id = lastId();
        ddl.addSQL(selectById());
        ddl.preparingStatement().setLong(1, id);
        ddl.build();

        while(ddl.getResult().next()){
            plugin = PluginFactory.getPluginByResultSet(ddl.getResult());

        }
        return plugin;
    }

    public Long lastId() throws SQLException {
        Long lastID = null;
        ddl.addSQL(maxId()).build();
        while(ddl.getResult().next()){
            lastID = ddl.getResult().getLong(1);
        }
        return lastID;
    }

    public Plugin findById(Long id) throws SQLException {
        Plugin plugin = null;

        ddl.addSQL(selectById());
        ddl.preparingStatement().setLong(1, id);
        ddl.build();
        while(ddl.getResult().next()){
            plugin = PluginFactory.getPluginByResultSet(ddl.getResult());
        }
        return plugin;
    }

    public Long nextId() throws SQLException {
        return lastId() + 1;
    }

    public Plugin lastPlugins() throws SQLException {
        return findById(lastId());
    }

    public boolean create(Plugin plugin) throws SQLException {
        dml.addSQL(insert());
        dml.preparingStatement().setLong(1, nextId());
        dml.preparingStatement().setString(2, plugin.getName());
        dml.preparingStatement().setString(3, plugin.getDescription());

        //executeUpdate: return number of rows inserted
        return dml.build().getResultValue() > 0;
    }

    public boolean update(Plugin updatedPlugin, Long id) throws SQLException {
        //First get the current user values
        Plugin oldPlugin = findById(id);

        dml.addSQL(updateById());
        dml.preparingStatement().setString(1, updatedPlugin.getName() != null ? updatedPlugin.getName() : oldPlugin.getName());
        dml.preparingStatement().setString(2, updatedPlugin.getDescription() != null ? updatedPlugin.getDescription() : oldPlugin.getDescription());
        dml.preparingStatement().setDate(3, updatedPlugin.getStartDate() != null ? new Date(updatedPlugin.getStartDate().getTime()) : new Date( oldPlugin.getStartDate().getTime()));
        dml.preparingStatement().setLong(4, id);

        //executeUpdate: return number of rows inserted
        return dml.build().getResultValue() > 0;
    }

    public boolean delete(Long id) throws SQLException {
        dml.addSQL(deleteById());
        dml.preparingStatement().setLong(1, id);
        return dml.build().getResultValue() > 0;
    }

    public boolean deleteAllElements() throws  SQLException{
        dml.addSQL(deleteAll());
        return dml.build().getResultValue() > 0;
    }
}
