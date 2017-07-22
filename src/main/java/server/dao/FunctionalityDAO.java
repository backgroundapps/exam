package server.dao;

import common.Functionality;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;
import server.factories.FunctionalityFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.FunctionalityQueries.*;

public class FunctionalityDAO {
    private StatementDDLBuilder ddl;
    private StatementDMLBuilder dml;


    public FunctionalityDAO() {    }

    public FunctionalityDAO(StatementDDLBuilder ddl) {
        this.ddl = ddl;
    }


    public FunctionalityDAO(StatementDDLBuilder ddl, StatementDMLBuilder dml) {
        this.ddl = ddl;
        this.dml = dml;
    }

    public Long numberOfFunctionalities() throws SQLException {
        Long id = null;
        ddl.addSQL(countId()).build();

        while(ddl.getResult().next()){
            id = ddl.getResult().getLong(1);
        }
        return id;
    }

    public List<Functionality> listFunctionalities() throws SQLException {
        List<Functionality> list = new ArrayList<>();
        ddl.addSQL(selectAll()).build();
        while(ddl.getResult().next()){
            list.add(FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult()));
        }
        return list;
    }

    public Functionality lastFunctionality() throws SQLException {
        Functionality functionality = null;
        Long id = lastId();
        ddl.addSQL(selectById());
        ddl.preparingStatement().setLong(1, id);
        ddl.build();

        while(ddl.getResult().next()){
            functionality = FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult());

        }
        return functionality;

    }

    public Long lastId() throws SQLException {
        Long lastID = null;
        ddl.addSQL(maxId()).build();
        while(ddl.getResult().next()){
            lastID = ddl.getResult().getLong(1);
        }
        return lastID;
    }

    public Functionality findById(Long id) throws SQLException {
        Functionality functionality = null;

        ddl.addSQL(selectById());
        ddl.preparingStatement().setLong(1, id);
        ddl.build();
        while(ddl.getResult().next()){
            functionality = FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult());
        }
        return functionality;

    }

    public Functionality findByName(String name) throws SQLException{
        Functionality functionality = null;

        ddl.addSQL(selectByName());
        ddl.preparingStatement().setString(1, name);
        ddl.build();
        while(ddl.getResult().next()){
            functionality = FunctionalityFactory.getFunctionalityByResultSet(ddl.getResult());
        }
        return functionality;
    }


    public Long nextId() throws SQLException {
        return lastId() + 1;

    }

    public boolean create(Functionality functionality) throws SQLException {
        dml.addSQL(insert());
        dml.preparingStatement().setLong(1, nextId());
        dml.preparingStatement().setString(2, functionality.getName());
        dml.preparingStatement().setString(3, functionality.getDescription());
        dml.preparingStatement().setLong(4, functionality.getPluginId());

        //executeUpdate: return number of rows inserted
        return dml.build().getResultValue() > 0;
    }

    public boolean update(Functionality updatedFunctionality, Long id) throws SQLException {
        //First get the current user values
        Functionality oldFunctionality = findById(id);

        dml.addSQL(updateById());
        dml.preparingStatement().setString(1, updatedFunctionality.getName() != null ? updatedFunctionality.getName() : oldFunctionality.getName());
        dml.preparingStatement().setString(2, updatedFunctionality.getDescription() != null ? updatedFunctionality.getDescription() : oldFunctionality.getDescription());
        dml.preparingStatement().setDate(3, updatedFunctionality.getStartDate() != null ? new Date(updatedFunctionality.getStartDate().getTime()) : new Date( oldFunctionality.getStartDate().getTime()));
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
