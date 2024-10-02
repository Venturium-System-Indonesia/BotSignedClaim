package attribute;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AKoneksiData {
//    Logger log = Logger.getLogger(AKoneksiData.class);

    public MKoneksiData getAtributeKoneksiData() {
        MKoneksiData data = new MKoneksiData();
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("assets/konfigurasi.properties"));
//            log.info("MKoneksiData getAtributeKoneksiData() is successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        data.setDriverDB(prop.getProperty("driver"));
        data.setUrlDB(prop.getProperty("url"));
        data.setUserDb(prop.getProperty("user"));
        data.setPasswordDb(prop.getProperty("password"));
        return data;
    }
}
