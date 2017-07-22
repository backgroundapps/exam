package server.dao;

import common.User;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;
import server.dao.utils.Statementable;
import server.factories.UserFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.UserQueries.*;

public class UserDAO {
    private StatementDDLBuilder ddl;
    private StatementDMLBuilder dml;

    public UserDAO() {
    }

    public UserDAO(StatementDDLBuilder ddl) {
        this.ddl = ddl;
    }

    public UserDAO(StatementDDLBuilder ddl, StatementDMLBuilder dml) {
        this.ddl = ddl;
        this.dml = dml;
    }

    public List<User> listUsers() throws SQLException {
        ddl.addSQL(selectAll()).build();
        List<User> list = new ArrayList<>();
        while(ddl.getResult().next()){
            list.add(UserFactory.getUserByResultSet(ddl.getResult()));
        }
        return list;
    }

    public Long lastId() throws SQLException {
        Long lastID = null;

        ddl.addSQL(maxId()).build();
        while(ddl.getResult().next()){
            lastID = ddl.getResult().getLong(1);
        }
        return lastID;
    }

    public Long numberOfUsers() throws SQLException {
        Long id = null;
        ddl.addSQL(countId()).build();

        while(ddl.getResult().next()){
            id = ddl.getResult().getLong(1);
        }
        return id;
    }


    public Long nextId() throws SQLException {
        return lastId() + 1;
    }

    public User findById(long id) throws SQLException {
        User user = null;

        ddl.addSQL(selectById());
        ddl.preparingStatement().setLong(1, id);
        ddl.build();
        while(ddl.getResult().next()){
            user = UserFactory.getUserByResultSet(ddl.getResult());
        }
        return user;
    }

    public boolean isValidLogin(String login) throws SQLException {
        ddl.addSQL(selectByLogin());
        ddl.preparingStatement().setString(1, login);
        ddl.build();
        return ddl.getResult().next();
    }

    public boolean create(User user) throws SQLException {

        dml.addSQL(insert());
        dml.preparingStatement().setLong(1, nextId());
        dml.preparingStatement().setString(2, user.getLogin());
        dml.preparingStatement().setString(3, user.getFullName());
        dml.preparingStatement().setString(4, user.getStatus());
        dml.preparingStatement().setString(5, user.getCurrentManagement());

        //executeUpdate: return number of rows inserted
        return dml.build().getResultValue() > 0;
    }

    public boolean update(User updatedUser, Long id) throws SQLException {
        //First get the current user values
        User oldUser = findById(id);

        dml.addSQL(updateById());
        dml.preparingStatement().setString(1, updatedUser.getLogin() != null ? updatedUser.getLogin() : oldUser.getLogin());
        dml.preparingStatement().setString(2, updatedUser.getFullName() != null ? updatedUser.getFullName() : oldUser.getFullName());
        dml.preparingStatement().setString(3, updatedUser.getStatus() != null ? updatedUser.getStatus() : oldUser.getStatus());
        dml.preparingStatement().setString(4, updatedUser.getCurrentManagement() != null ? updatedUser.getCurrentManagement() : oldUser.getCurrentManagement());
        dml.preparingStatement().setLong(5, id);

        //executeUpdate: return number of rows inserted
        return dml.build().getResultValue() > 0;
    }


    public User lastUser() throws SQLException {
        return findById(lastId());
    }

    public boolean cancelUser(Long id) throws SQLException {
        dml.addSQL(updateStatusById());
        dml.preparingStatement().setString(1, "INACTIVE");
        dml.preparingStatement().setLong(2, id);

        //executeUpdate: return number of rows updated
        return dml.build().getResultValue() > 0;
    }

    public boolean delete(Long id) throws SQLException {
        dml.addSQL(deleteById());
        dml.preparingStatement().setLong(1, id);
        return dml.build().getResultValue() > 0;
    }

    //Just for run all tests
    public boolean deleteAllElements() throws  SQLException{
        dml.addSQL(deleteAll());
        return dml.build().getResultValue() > 0;
    }

}
