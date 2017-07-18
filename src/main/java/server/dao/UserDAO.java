package server.dao;

import common.User;
import common.UserImpl;
import server.factories.UserFactory;
import server.dao.conf.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public enum FIELDS { ID, LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT }
    private Connection con;

    public List<User> listUsers() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;
        ResultSet rst = null;

        try{
            ps = con.prepareStatement("SELECT * FROM USERS");
            rst = ps.executeQuery();
            List<User> list = new ArrayList<>();
            while(rst.next()){
                //User user = new UserImpl(rst.getLong(FIELDS.ID.name()), rst.getString(FIELDS.LOGIN.name()), rst.getString(FIELDS.FULL_NAME.name()), rst.getString(FIELDS.STATUS.name()), rst.getString(FIELDS.CURRENT_MANAGEMENT.name()));
                list.add(UserFactory.getUserByResultSet(rst));
            }
            return list;

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }

    public Long lastId() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rst = null;
        Long lastID = null;

        try{
            ps = con.prepareStatement("SELECT MAX(ID) FROM USERS");
            rst = ps.executeQuery();
            while(rst.next()){
                lastID = rst.getLong(1);
            }
            return lastID;

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }

    public Long numberOfUsers() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;
        ResultSet rst = null;
        Long id = null;

        try{
            ps = con.prepareStatement("SELECT COUNT(ID) FROM USERS");
            rst = ps.executeQuery();
            while(rst.next()){
                id = rst.getLong(1);
            }
            return id;

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }


    public Long nextId() throws SQLException {
        return lastId() + 1;
    }

    public User findById(long id) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;
        ResultSet rst = null;
        User user = null;

        try{
            ps = con.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            ps.setLong(1, id);

            rst = ps.executeQuery();
            while(rst.next()){
                user = UserFactory.getUserByResultSet(rst);
            }
            return user;

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }


    public boolean create(UserImpl user) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;

        String sql = "INSERT INTO USERS (ID, LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT) VALUES (?, ?, ?, ?, ?)";

        try{
            ps = con.prepareStatement(sql);
            ps.setLong(1, nextId());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getStatus());
            ps.setString(5, user.getCurrentManagement());

            //executeUpdate: return number of rows inserted
            return ps.executeUpdate() > 0;

        }finally {
            ps.close();
            con.close();
        }
    }

    public boolean update(UserImpl updatedUser, Long id) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;

        try{
            //First get the current user values
            User oldUser = findById(id);

                String sql = "UPDATE USERS SET LOGIN=?, FULL_NAME=?, STATUS=? ,CURRENT_MANAGEMENT=? WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, updatedUser.getLogin() != null ? updatedUser.getLogin() : oldUser.getLogin());
            ps.setString(2, updatedUser.getFullName() != null ? updatedUser.getFullName() : oldUser.getFullName());
            ps.setString(3, updatedUser.getStatus() != null ? updatedUser.getStatus() : oldUser.getStatus());
            ps.setString(4, updatedUser.getCurrentManagement() != null ? updatedUser.getCurrentManagement() : oldUser.getCurrentManagement());
            ps.setLong(5, id);

            //executeUpdate: return number of rows inserted
            return ps.executeUpdate() > 0;

        }finally {
            ps.close();
            con.close();
        }
    }


    public User lastUser() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;
        ResultSet rst = null;
        User user = null;

        try{
            Long id = lastId();
            ps = con.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            ps.setLong(1, id);

            rst = ps.executeQuery();
            while(rst.next()){
                user = UserFactory.getUserByResultSet(rst);

            }
            return user;

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }

    public boolean cancelUser(Long id) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;

        try{
            String sql = "UPDATE USERS SET STATUS=? WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "INACTIVE");
            ps.setLong(2, id);

            //executeUpdate: return number of rows updated
            return ps.executeUpdate() > 0;

        }finally {
            ps.close();
            con.close();
        }
    }

    public boolean delete(Long id) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;

        try{
            String sql = "DELETE FROM USERS WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            //executeUpdate: return number of rows REMOVED
            return ps.executeUpdate() > 0;

        }finally {
            ps.close();
            con.close();
        }
    }

}
