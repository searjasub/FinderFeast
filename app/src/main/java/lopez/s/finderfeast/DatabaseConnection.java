package lopez.s.finderfeast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {

    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "postgres://kkopgmkk:2EraHzyJtA3u6HLfnolQ9-PmoJT9K1PO@rajje.db.elephantsql.com:5432/kkopgmkk";
        String username = "kkopgmkk";
        String password = "2EraHzyJtA3u6HLfnolQ9-PmoJT9K1PO";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM test");
            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(2));
            }
            rs.close();
            st.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
