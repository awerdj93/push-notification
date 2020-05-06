package backend;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties("app-properties")
public class AppProperties{



    private String PRODUCT_URL_KEY;
    private String EMAIL_USERNAME;
    private String EMAIL_PASSWORD;
    private String GOOGLE_API_KEY;

    public void setPRODUCT_URL_KEY(String PRODUCT_URL_KEY) {
        this.PRODUCT_URL_KEY = PRODUCT_URL_KEY;
    }

    public void setEMAIL_USERNAME(String EMAIL_USERNAME) {
        this.EMAIL_USERNAME = EMAIL_USERNAME;
    }

    public void setEMAIL_PASSWORD(String EMAIL_PASSWORD) {
        this.EMAIL_PASSWORD = EMAIL_PASSWORD;
    }

    public void setGOOGLE_API_KEY(String GOOGLE_API_KEY) {
        this.GOOGLE_API_KEY = GOOGLE_API_KEY;
    }



    public String getPRODUCT_URL_KEY(){
        return PRODUCT_URL_KEY;
    }

    public String getEMAIL_USERNAME(){
        return EMAIL_USERNAME;
    }

    public String getEMAIL_PASSWORD(){
        return EMAIL_PASSWORD;
    }

    public String getGOOGLE_API_KEY(){

        return GOOGLE_API_KEY;
    }





}