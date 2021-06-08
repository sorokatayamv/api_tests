package daraprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.annotations.DataProvider;

public class TestData {

    private static Properties testDataProps;
    private static String testDataPropsPath = "src/main/resources/trello.properties";

    @DataProvider
    public static Object[][] boardNames() {
        return new Object[][] {
            {getTestDataProps().getProperty("board_name_en")},
            {getTestDataProps().getProperty("board_name_with_symbols")},
            {getTestDataProps().getProperty("board_name_short")},
            {getTestDataProps().getProperty("board_name_long")},
        };
    }

    static {
        testDataProps = new Properties();
        try (InputStream in = new FileInputStream(testDataPropsPath)) {
            testDataProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getTestDataProps() {
        return testDataProps;
    }
}
