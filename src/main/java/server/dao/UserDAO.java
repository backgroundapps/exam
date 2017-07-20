package server.dao;

import common.User;
import common.UserImpl;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.Statementable;
import server.factories.UserFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.dao.queries.UserQueries.*;

public class UserDAO {

    public List<User> listUsers() throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try{
            ddl.addSQL(selectAll()).build();
            List<User> list = new ArrayList<>();
            while(ddl.getResult().next()){
                //User user = new UserImpl(rst.getLong(FIELDS.ID.name()), rst.getString(FIELDS.LOGIN.name()), rst.getString(FIELDS.FULL_NAME.name()), rst.getString(FIELDS.STATUS.name()), rst.getString(FIELDS.CURRENT_MANAGEMENT.name()));
                list.add(UserFactory.getUserByResultSet(ddl.getResult()));
            }
            return list;

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

    public Long numberOfUsers() throws SQLException {
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


    public Long nextId() throws SQLException {
        return lastId() + 1;
    }

    public User findById(long id) throws SQLException {
        Statementable ddl = StatementBuilderFactory.getDDLBuilderInstance();
        User user = null;

        try{
            ddl.addSQL(selectById());
            ddl.preparingStatement().setLong(1, id);
            ddl.build();
            while(ddl.getResult().next()){
                user = UserFactory.getUserByResultSet(ddl.getResult());
            }
            return user;

        }finally {
            ddl.close();
        }
    }


    public boolean create(UserImpl user) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            dml.addSQL(insert());
            dml.preparingStatement().setLong(1, nextId());
            dml.preparingStatement().setString(2, user.getLogin());
            dml.preparingStatement().setString(3, user.getFullName());
            dml.preparingStatement().setString(4, user.getStatus());
            dml.preparingStatement().setString(5, user.getCurrentManagement());

            //executeUpdate: return number of rows inserted
            return dml.build().getResultValue() > 0;

        }finally {
            dml.close();
        }
    }

    public boolean update(UserImpl updatedUser, Long id) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();
        try{
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

        }finally {
            dml.close();
        }
    }


    public User lastUser() throws SQLException {
        return findById(lastId());
    }

    public boolean cancelUser(Long id) throws SQLException {
        Statementable dml = StatementBuilderFactory.getDMLBuilderInstance();

        try{
            dml.addSQL(updateStatusById());
            dml.preparingStatement().setString(1, "INACTIVE");
            dml.preparingStatement().setLong(2, id);

            //executeUpdate: return number of rows updated
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
