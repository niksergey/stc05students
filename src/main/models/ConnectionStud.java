package main.models;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by sergey on 19.04.17.
 */
public class ConnectionStud {
    public static void connect() {
        try {
            InitialContext cxt = null;
            cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
