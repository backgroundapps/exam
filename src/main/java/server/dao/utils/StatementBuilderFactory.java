package server.dao.utils;

public class StatementBuilderFactory {
    public static StatementDDLBuilder getDDLBuilder(){
        return new StatementDDLBuilder();
    };
}
