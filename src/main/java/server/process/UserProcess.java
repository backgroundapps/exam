package server.process;

import common.User;
import server.dao.UserDAO;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;

import java.sql.SQLException;
import java.util.List;

public class UserProcess {

    public List<User> getUsers() throws SQLException {
            StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).listUsers();

        }finally {
            ddl.close();
        }
    }

    public Long getNumberOfUsers() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).numberOfUsers();

        }finally {
            ddl.close();
        }
    }

    public Long lastId() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).lastId();

        }finally {
            ddl.close();
        }
    }

    public User lastUser() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).lastUser();

        }finally {
            ddl.close();
        }
    }


    public Long nextId() throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).nextId();

        }finally {
            ddl.close();
        }
    }

    public User findById(Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).findById(id);

        }finally {
            ddl.close();
        }
    }

    public boolean create(User user) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();
        try {
            return new UserDAO(ddl, dml).create(user);

        }finally {
            ddl.close();
            dml.close();

        }
    }

    public boolean update(User user, Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();
        try {
            return new UserDAO(ddl, dml).update(user, id);

        }finally {
            ddl.close();
            dml.close();

        }
    }

    public boolean cancelUser(Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();
        try {
            return new UserDAO(ddl, dml).cancelUser(id);

        }finally {
            ddl.close();
            dml.close();

        }
    }

    public boolean delete(Long id) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();
        try {
            return new UserDAO(ddl, dml).delete(id);

        }finally {
            ddl.close();
            dml.close();

        }
    }


    public boolean isValidLogin(String login) throws SQLException {
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            return new UserDAO(ddl).isValidLogin(login);

        }finally {
            ddl.close();
        }
    }
}
