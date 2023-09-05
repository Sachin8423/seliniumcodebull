package com.code.bull.driver;
import com.code.bull.commonutils.applicationutils.constants.ApplicationConstants;
import com.code.bull.commonutils.applicationutils.constants.ConstantUtils;
import com.code.bull.pagerepository.pagemathods.PageCollection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.lang.invoke.MethodHandles;

        public class Driver {
             protected static String env =System.getProperty("env");
            private static final Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
            //protected static String browser =null ;
            public static WebDriver driver;
            public static ConstantUtils constants = ConstantUtils.getInstance();
            public static PageCollection pages;

        @BeforeSuite
        public void setupEnv(){
        envSetup();
        setBrowser();

    }
        @BeforeClass
        public void pageSetup(){
            try {
                pages = new PageCollection(driver);
            }catch (Exception e){
                log.error(e.getMessage());
            }

        }
        @BeforeMethod
        public static void mathodLevelSetup(){

        }
        @AfterMethod
        public static void endTest(){

        }
        @AfterClass
        public void cleanup(){

        }
        @AfterSuite
        public void close(){
            driver.close();
            if(driver!=null){
                driver.quit();
            }


        }

        private void envSetup(){
            log.info("Going to setup Env");
            //browser= constants.getvalue(ApplicationConstants.WEB_BROWSER);
           if(env.equalsIgnoreCase("sit")) {
            env = "SIT";
        }   else if (env.equalsIgnoreCase("uat")) {
            env = "UAT";
        }  else if (env.equalsIgnoreCase("prod")) {
            env = "PROD";
        }   else {
            env ="SIT";

            }

        }
        private void setBrowser() {

           try {
             switch (browser) {
                 case "chrome":
                    WebDriverManager.chromedriver().setup();
                    browserCapabilities();
                    break;
                 case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    break;
                 case "Safari":
                    WebDriverManager.safaridriver().setup();
                    break;
                 default :
                    break;
            }
        } catch(Exception e){
                log.error("error in setBrowser method and it is :"+e.getMessage());
        }
    }
          private void browserCapabilities(){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("window-size=1792,1120");
            options.setHeadless(false);
            driver= new ChromeDriver(options);
            driver.manage().window().maximize();
            }
        }










