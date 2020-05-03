package backend.email;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import backend.model.Seller;
import backend.model.User;


public class NeighbouringUser {
	@Autowired
	private Environment env;	
	
	private final String PRODUCT_URL_KEY = "microservices.product.url";	
	private final String EMAIL_USERNAME = "email.username";
	private final String EMAIL_PASSWORD = "email.password";
	private final String GOOGLE_API_KEY = "google.api.key";
	private final String GOOGLE_DISTANCE_API = "https://maps.googleapis.com/maps/api/distancematrix/json";
	
	
    public User neighbouringUser(User user, Seller seller) throws Exception {
        String userAddr=user.getUserAddr();
        String sellerAddr=seller.getSellerAddr();
        double ss=distance(userAddr,sellerAddr);
        User user1= new User();
        if (ss<=5.0){
        	
        	String username = env.getProperty(EMAIL_USERNAME);
            String password = env.getProperty(EMAIL_PASSWORD);
            System.out.println(username);
            BeanUtils.copyProperties(user, user1);
            String email=user.getUserEmail();
            Long productId=seller.getProductId();

            String productLink = env.getProperty(PRODUCT_URL_KEY) + "/products/"+productId;
            if (email!=null){
                SendEmailHTML.sendmail(username, password, email, "Following products are available near you","<h1>You might like the following " +
                        "products available near you </h1>"+ productLink);
            }
        }
        return user1;
    }

    public <parseString> double distance(String origins, String destinations) throws Exception{
        try {
            origins = URLEncoder.encode(origins, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            destinations = URLEncoder.encode(destinations, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String httpsURL = env.getProperty(GOOGLE_DISTANCE_API) + "?origins="+origins+"&destinations="+destinations+"&key=" + env.getProperty(GOOGLE_API_KEY);
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String inputLine;
        inputLine=br.readLine();
        StringBuilder responseStrBuilder = new StringBuilder();
        while ((inputLine=br.readLine())!=null){
            responseStrBuilder.append(inputLine+ "\n");
        }
        br.close();
        JSONObject jsonObject = new JSONObject("{\n"+responseStrBuilder.toString());
        JSONArray val=jsonObject.getJSONArray("rows");
        JSONObject val1=val.getJSONObject(0);
        JSONArray distance=val1.getJSONArray("elements");
        JSONObject distance_in_km=distance.getJSONObject(0);
        JSONObject distance_in_km1=distance_in_km.getJSONObject("distance");
        Object distance_in_km2=distance_in_km1.get("text");
        String  distance1=distance_in_km2.toString().split(" ",2)[0];
        double distance2;
        try{
            distance2=Double.parseDouble(distance1.split(",",2)[0]+distance1.split(",",2)[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            distance2=Double.parseDouble(distance1);
        }
        return distance2;
    }
}
