package server.dao.queries;

public class FunctionalityQueries {
    public enum FIELDS { ID, NAME, DESCRIPTION, START_DATE, PLUGIN_ID}
    /**
     * String query with one parameter
     * 1 - ID
     * @return
     */
    public static String deleteById(){
        return "DELETE FROM FUNCTIONALITIES WHERE ID=?";
    }

    public static String maxId(){
        return "SELECT MAX(ID) FROM FUNCTIONALITIES";
    }

    /**
     * String query with one parameter
     * 1 - ID
     * @return
     */
    public static String selectById(){
        return "SELECT * FROM FUNCTIONALITIES WHERE ID = ?";
    }

    public static String selectAll(){
        return "SELECT * FROM FUNCTIONALITIES";
    }

    public static String countId(){
        return "SELECT COUNT(ID) FROM FUNCTIONALITIES";
    }

    /**
     * String query with 3 parameters
     * 1 - ID
     * 2 - NAME
     * 3 - DESCRIPTION
     *
     * @return
     */
    public static String insert(){
        return "INSERT INTO FUNCTIONALITIES (ID, NAME, DESCRIPTION, PLUGIN_ID) VALUES (?, ?, ?, ?)";
    }

    /**
     * String query with 4 parameters
     * 1 - NAME
     * 2 - DESCRIPTION
     * 3 - START_DATE
     *
     * 4 - ID: Functionality in the where
     * @return
     */
    public static String updateById(){
        return "UPDATE FUNCTIONALITIES SET NAME=?, DESCRIPTION=?, START_DATE=? WHERE ID=?";
    }

    public static String deleteAll(){
        return "DELETE FROM FUNCTIONALITIES";
    }

}
