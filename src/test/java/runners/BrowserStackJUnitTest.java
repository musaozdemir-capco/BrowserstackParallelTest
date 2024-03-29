package runners;


import com.browserstack.local.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import testBase.PageInitializer;

import java.io.FileReader;
import java.net.URL;
import java.util.*;


//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features" ,
//        glue = "stepDefinitions",
//        dryRun = false,
//        monochrome = true,
//        tags = "@smoke",
//        plugin = {"pretty",
//                "html:target/cucumber-default-reports",
//                "rerun:target/failedTest.txt",
//                "json:target/cucumber.json"
//        }
//)

@RunWith(Parallelized.class)
public class BrowserStackJUnitTest {

        protected static WebDriver driver;
        private Local l;

        private static JSONObject config;

        @Parameterized.Parameter(value = 0)
        public int taskID;

        @Parameterized.Parameters
        public static Iterable<? extends Object> data() throws Exception {
                List<Integer> taskIDs = new ArrayList<Integer>();

                if(System.getProperty("config") != null) {
                        JSONParser parser = new JSONParser();
                        config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + System.getProperty("config")));
                        int envs = ((JSONArray)config.get("environments")).size();

                        for(int i=0; i<envs; i++) {
                                taskIDs.add(i);
                        }
                }

                return taskIDs;
        }
        @Before
        public void setUp() throws Exception {
                JSONArray envs = (JSONArray) config.get("environments");

                DesiredCapabilities capabilities = new DesiredCapabilities();

                Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskID);
                Iterator it = envCapabilities.entrySet().iterator();
                while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                }

                Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
                it = commonCapabilities.entrySet().iterator();
                while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        if(capabilities.getCapability(pair.getKey().toString()) == null){
                                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                        }
                }

                String username = System.getenv("BROWSERSTACK_USERNAME");
                if(username == null) {
                        username = (String) config.get("user");
                }

                String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
                if(accessKey == null) {
                        accessKey = (String) config.get("key");
                }

                if(capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true"){
                        l = new Local();
                        Map<String, String> options = new HashMap<String, String>();
                        options.put("key", accessKey);
                        l.start(options);
                }

                driver = new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);
                PageInitializer.initializePageObjects();
        }

        @After
        public void tearDown() throws Exception {
                driver.quit();
                if(l != null) l.stop();
        }



}

