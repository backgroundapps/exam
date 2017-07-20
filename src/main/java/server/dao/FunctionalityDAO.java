package server.dao;

import common.Functionality;
import common.FunctionalityImpl;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.Statementable;
import server.factories.FunctionalityFactory;
import server.factories.PluginFactory;

import static server.dao.queries.FunctionalityQueries.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionalityDAO {

    public Long numberOfFunctionalities() throws SQLException {
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

    public List<Functionality> listFunctionalities() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try{
            List<Functionality> list = new ArrayList<>();
            ddl.addSQL(selectAll()).build();
            while(ddl.getResult().next()){
                list.add(FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult()));
            }
            return list;

        }finally {
            ddl.close();
        }

    }

    public Functionality lastFunctionality() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Functionality functionality = null;

        try{
            Long id = lastId();
            ddl.addSQL(selectById());
            ddl.preparingStatement().setLong(1, id);
            ddl.build();

            while(ddl.getResult().next()){
                functionality = FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult());

            }
            return functionality;

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

    public Functionality findById(Long id) throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        Functionality functionality = null;

        try{
            ddl.addSQL(selectById());
            ddl.preparingStatement().setLong(1, id);
            ddl.build();
            while(ddl.getResult().next()){
                functionality = FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult());
            }
            return functionality;

        }finally {
            ddl.close();
        }

    }

    public Long nextId() throws SQLException {
        return lastId() + 1;

    }

    public boolean create(FunctionalityImpl functionality) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();
        try{
            dml.addSQL(insert());
            dml.preparingStatement().setLong(1, nextId());
            dml.preparingStatement().setString(2, functionality.getName());
            dml.preparingStatement().setString(3, functionality.getDescription());
            dml.preparingStatement().setLong(4, functionality.getPlugin().getId());

            //executeUpdate: return number of rows inserted
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }

    }

    public boolean update(FunctionalityImpl updatedFunctionality, Long id) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            //First get the current user values
            Functionality oldFunctionality = findById(id);

            dml.addSQL(updateById());
            dml.preparingStatement().setString(1, updatedFunctionality.getName() != null ? updatedFunctionality.getName() : oldFunctionality.getName());
            dml.preparingStatement().setString(2, updatedFunctionality.getDescription() != null ? updatedFunctionality.getDescription() : oldFunctionality.getDescription());
            dml.preparingStatement().setDate(3, updatedFunctionality.getStartDate() != null ? new Date(updatedFunctionality.getStartDate().getTime()) : new Date( oldFunctionality.getStartDate().getTime()));
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
