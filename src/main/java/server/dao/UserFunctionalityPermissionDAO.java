package server.dao;

import common.Functionality;
import common.User;
import common.UserFunctionality;
import common.UserFunctionalityImpl;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;
import server.factories.UserFunctionalityFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.UserFunctionalityPermissionQueries.*;

public class UserFunctionalityPermissionDAO {
    private StatementDDLBuilder ddl;
    private StatementDMLBuilder dml;

    public UserFunctionalityPermissionDAO(StatementDDLBuilder ddl) {
        this.ddl = ddl;
    }


    public UserFunctionalityPermissionDAO(StatementDDLBuilder ddl, StatementDMLBuilder dml) {
        this.ddl = ddl;
        this.dml = dml;
    }

    public Long numberOfUserFunctionalities() throws SQLException {
        Long id = null;
        ddl.addSQL(countId()).build();

        while(ddl.getResult().next()){
            id = ddl.getResult().getLong(1);
        }
        return id;
    }

    public List<UserFunctionality> listUserFunctionalities() throws SQLException {
        List<UserFunctionality> list = new ArrayList<>();
        ddl.addSQL(selectAll()).build();
        while(ddl.getResult().next()){
            list.add(UserFunctionalityFactory.getUserFunctionalityByResultSet(ddl.getResult()));
        }
        return list;
    }


    public List<UserFunctionality> findUsersFromFunctionality(Functionality functionality) throws SQLException {
        List<UserFunctionality> list = new ArrayList<>();
        ddl.addSQL(selectByFunctionalityId());
        ddl.preparingStatement().setLong(1, functionality.getId());
        ddl.build();

        while(ddl.getResult().next()){
            list.add(UserFunctionalityFactory.getUserFunctionalityByResultSet(ddl.getResult()));
        }
        return list;
    }

    public List<UserFunctionality> findFunctionalitiesByUser(User user) throws SQLException {
        List<UserFunctionality> list = new ArrayList<>();
        ddl.addSQL(selectByUserId());
        ddl.preparingStatement().setLong(1, user.getId());
        ddl.build();

        while(ddl.getResult().next()){
            list.add(UserFunctionalityFactory.getUserFunctionalityByResultSet(ddl.getResult()));
        }
        return list;
    }


    public boolean addPermission(User user, Functionality functionality) throws SQLException {
            dml.addSQL(insert());
            dml.preparingStatement().setLong(1, user.getId());
            dml.preparingStatement().setLong(2, functionality.getId());
            return dml.build().getResultValue() > 0;
    }

    public boolean removePermission(User user, Functionality functionality) throws SQLException {
        dml.addSQL(deleteByUserIdAndFunctionalityId());
        dml.preparingStatement().setLong(1, user.getId());
        dml.preparingStatement().setLong(2, functionality.getId());
        return dml.build().getResultValue() > 0;
    }

    public boolean deleteAllElements() throws SQLException {
        dml.addSQL(deleteAll());
        return dml.build().getResultValue() > 0;
    }

    public boolean removePermissionById(Long user, Long functionality) throws SQLException {
        dml.addSQL(deleteByUserIdAndFunctionalityId());
        dml.preparingStatement().setLong(1, user);
        dml.preparingStatement().setLong(2, functionality);
        return dml.build().getResultValue() > 0;
    }


}
