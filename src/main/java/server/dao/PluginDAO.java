package server.dao;

import common.Plugin;
import common.PluginImpl;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.Statementable;
import server.factories.PluginFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.PluginQueries.*;

public class PluginDAO {

    public Long numberOfPlugins() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Long id = null;

        try{
            ddl.addSQL(countId()).build();

            while(ddl.getResult().next()){
                id = ddl.getResult().getLong(1);
            }
            return id;

        }finally {
            ddl.close();
        }
    }

    public List<Plugin> listPlugins() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try{
            List<Plugin> list = new ArrayList<>();
            ddl.addSQL(selectAll()).build();
            while(ddl.getResult().next()){
                list.add(PluginFactory.getPluginByResultSet(ddl.getResult()));
            }
            return list;

        }finally {
            ddl.close();
        }
    }

    public Plugin lastPlugin() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Plugin plugin = null;

        try{
            Long id = lastId();
            ddl.addSQL(selectById());
            ddl.preparingStatement().setLong(1, id);
            ddl.build();

            while(ddl.getResult().next()){
                plugin = PluginFactory.getPluginByResultSet(ddl.getResult());

            }
            return plugin;

        }finally {
            ddl.close();
        }
    }

    public Long lastId() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Long lastID = null;

        try{
            ddl.addSQL(maxId()).build();
            while(ddl.getResult().next()){
                lastID = ddl.getResult().getLong(1);
            }
            return lastID;

        }finally {
            ddl.close();
        }
    }

    public Plugin findById(Long id) throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Plugin plugin = null;

        try{
            ddl.addSQL(selectById());
            ddl.preparingStatement().setLong(1, id);
            ddl.build();
            while(ddl.getResult().next()){
                plugin = PluginFactory.getPluginByResultSet(ddl.getResult());
            }
            return plugin;

        }finally {
            ddl.close();
        }
    }

    public Long nextId() throws SQLException {
        return lastId() + 1;
    }

    public Plugin lastPlugins() throws SQLException {
        return findById(lastId());
    }

    public boolean create(PluginImpl plugin) throws SQLException {

        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();
        try{
            dml.addSQL(insert());
            dml.preparingStatement().setLong(1, nextId());
            dml.preparingStatement().setString(2, plugin.getName());
            dml.preparingStatement().setString(3, plugin.getDescription());

            //executeUpdate: return number of rows inserted
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }
    }

    public boolean update(PluginImpl updatedPlugin, Long id) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            //First get the current user values
            Plugin oldPlugin = findById(id);

            dml.addSQL(updateById());
            dml.preparingStatement().setString(1, updatedPlugin.getName() != null ? updatedPlugin.getName() : oldPlugin.getName());
            dml.preparingStatement().setString(2, updatedPlugin.getDescription() != null ? updatedPlugin.getDescription() : oldPlugin.getDescription());
            dml.preparingStatement().setDate(3, updatedPlugin.getStartDate() != null ? new Date(updatedPlugin.getStartDate().getTime()) : new Date( oldPlugin.getStartDate().getTime()));
            dml.preparingStatement().setLong(4, id);

            //executeUpdate: return number of rows inserted
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }
    }

    public boolean delete(Long id) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            dml.addSQL(deleteById());
            dml.preparingStatement().setLong(1, id);
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }
    }

    public boolean deleteAllElements() throws  SQLException{
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            dml.addSQL(deleteAll());
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }
    }
}
