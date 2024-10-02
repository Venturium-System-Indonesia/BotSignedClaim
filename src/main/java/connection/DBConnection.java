package connection;

import attribute.AKoneksiData;
import attribute.MKoneksiData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection conn = null;
    AKoneksiData atribute = new AKoneksiData();
    MKoneksiData data = new MKoneksiData();
//    Logger log = Logger.getLogger(DBconnection.class);

    public DBConnection() {
        data = atribute.getAtributeKoneksiData();
        try {
            Class.forName(data.getDriverDB());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(data.getUrlDB(), data.getUserDb(), data.getPasswordDb());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        System.out.println("DBconnection getConnection()");
//        log.info("getConnection() is successfully");
        return this.conn;
    }

    public void closeConnection() {
        if (this.conn != null) {
            try {
                System.out.println("DBconnection closeConnection()");
//                log.info("closeConnection() is successfully");
                this.conn.close();
            } catch (Exception ex) {
                System.err.println(ex);
//                log.error("closeConnection():" + ex.toString());
            }
        }
    }
//    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
//        // TODO code application logic here
//        new DBconnection();
//    }
}
