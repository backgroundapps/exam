package unit;

import org.junit.Assert;
import org.junit.Test;
import server.dao.conf.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    @Test
    public void shouldReturnCurrentTime() throws SQLException, IOException {
        String result = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps =null;
        ResultSet rst = null;

        try{
            ps = con.prepareStatement("SELECT SYSDATE FROM DUAL");
            rst = ps.executeQuery();

            while (rst.next()) {
                result = rst.getString(1);
            }

            Assert.assertNotNull(result);

        }finally {
            rst.close();
            ps.close();
            con.close();
        }
    }

}
