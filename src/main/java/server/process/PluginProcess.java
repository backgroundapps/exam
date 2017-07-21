package server.process;

import common.Plugin;
import common.User;
import server.dao.PluginDAO;
import server.dao.UserDAO;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;

import java.sql.SQLException;
import java.util.List;

import static server.dao.queries.PluginQueries.updateById;

public class PluginProcess {
    public List<Plugin> getPlugins() throws SQLException {
            StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new PluginDAO(ddl).listPlugins();

        }finally {
            ddl.close();
        }
    }

    public Long numberOfPlugins() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new PluginDAO(ddl).numberOfPlugins();

        }finally {
            ddl.close();
        }
    }

    public Plugin lastPlugin() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new PluginDAO(ddl).lastPlugin();

        }finally {
            ddl.close();
        }
    }

    public Long lastId() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        try {
            return new PluginDAO(ddl).lastId();

        }finally {
            ddl.close();
        }
    }

    public Plugin findById(Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        try {
            return new PluginDAO(ddl).findById(id);

        }finally {
            ddl.close();
        }
    }

    public Long nextId() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        try {
            return new PluginDAO(ddl).nextId();

        }finally {
            ddl.close();
        }
    }

    public Plugin lastPlugins() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        try {
            return new PluginDAO(ddl).lastPlugins();

        }finally {
            ddl.close();
        }
    }

    public boolean create(Plugin plugin) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();

        try {
            return new PluginDAO(ddl, dml).create(plugin);

        }finally {
            ddl.close();
            dml.close();
        }
    }

    public boolean update(Plugin updatedPlugin, Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();

        try {
            return new PluginDAO(ddl, dml).update(updatedPlugin, id);

        }finally {
            ddl.close();
            dml.close();
        }
    }

    public boolean delete(Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();

        try {
            return new PluginDAO(ddl, dml).delete(id);

        }finally {
            ddl.close();
            dml.close();
        }
    }

}
