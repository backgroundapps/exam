package server.dao.utils;

import server.dao.conf.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementDDLBuilder implements Statementable{
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected String sql;


    public StatementDDLBuilder(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Statementable addSQL(String sql) {
        this.preparedStatement = null;

        this.sql = sql;
        return this;
    }

    @Override
    public void close() throws SQLException{
        this.resultSet.close();
        this.preparedStatement.close();
        this.connection.close();
    }

    @Override
    public Statementable build() throws SQLException {
        if(this.preparedStatement == null){
            this.preparedStatement = this.connection.prepareStatement(this.sql);

        }
        this.resultSet = this.preparedStatement.executeQuery();
        return this;
    }

    @Override
    public ResultSet getResult(){
        return this.resultSet;
    }

    @Override
    /**
     * TODO: Just because of the Interface. This method can not be called. Refactor!
     *
     */
    public int getResultValue() {
        new RuntimeException("This method can not be called. Try call for getResult");
        return 0;
    }

    @Override
    public PreparedStatement preparingStatement() throws SQLException{
        if(this.preparedStatement == null){
            this.preparedStatement = this.connection.prepareStatement(this.sql);

        }
        return preparedStatement;
    }

}
