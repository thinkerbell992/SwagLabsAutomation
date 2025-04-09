package Utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final String sPropertiesPath = "test.properties";
    

    public static Properties loadPropertiesFile(String sFilePath){
        Properties properties = new Properties();

        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(sFilePath);
        try{
            properties.load(inputStream);
        }catch (IOException e){
            Assert.fail("cannot load" + sFilePath + "file! Message:" + e.getMessage());
        }
        return properties;
    }
}
