package server.dao.queries;

public class UserQueries {
    public enum FIELDS { ID, LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT }
    /**
     * String query with one parameter
     * 1 - ID
     * @return
     */
    public static String deleteById(){
        return "DELETE FROM USERS WHERE ID=?";
    }

    public static String maxId(){
        return "SELECT MAX(ID) FROM USERS";
    }

    /**
     * String query with one parameter
     * 1 - ID
     * @return
     */
    public static String selectById(){
        return "SELECT * FROM USERS WHERE ID = ?";
    }

    public static String selectAll(){
        return "SELECT * FROM USERS";
    }

    public static String countId(){
        return "SELECT COUNT(ID) FROM USERS";
    }

    /**
     * String query with 5 parameters
     * 1 - ID
     * 2 - LOGIN
     * 3 - FULL_NAME
     * 4 - STATUS
     * 5 - CURRENT_MANAGEMENT
     *
     * @return
     */
    public static String insert(){
        return "INSERT INTO USERS (ID, LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT) VALUES (?, ?, ?, ?, ?)";
    }

    /**
     * String query with 5 parameters
     * 1 - LOGIN
     * 2 - FULL_NAME
     * 3 - STATUS
     * 4 - CURRENT_MANAGEMENT
     *
     * 5 - ID: Used in the where
     * @return
     */
    public static String updateById(){
        return "UPDATE USERS SET LOGIN=?, FULL_NAME=?, STATUS=? ,CURRENT_MANAGEMENT=? WHERE ID=?";
    }

    /**
     * String query with 2 parameter
     * 1 - STATUS
     * 2 - ID
     * @return
     */
    public static String updateStatusById(){
        return "UPDATE USERS SET STATUS=? WHERE ID=?";
    }

    public static String deleteAll(){
        return "DELETE FROM USERS";
    }
}
