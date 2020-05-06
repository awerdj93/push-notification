package backend.email;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import backend.model.Seller;
import backend.model.User;


public class NeighbouringUser {


    public User neighbouringUser(User user, Seller seller, String PRODUCT_URL,String username, String password, String GOOGLE_DISTANCE_API, String GOOGLE_API_KEY, String DELETE_URL) throws Exception {


        String userAddr=user.getUserAddr();
        String sellerAddr=seller.getSellerAddr();
        double ss=distance(userAddr,sellerAddr, GOOGLE_DISTANCE_API, GOOGLE_API_KEY);


        User user1= new User();
        if (ss<=5.0){


            BeanUtils.copyProperties(user, user1);
            String email=user.getUserEmail();
            Long productId=seller.getProductId();
            Long userId = user1.getUserId();
            Long subId = user1.getId();

            String productLink = PRODUCT_URL + "/products/"+productId;
            String unsubscriptionLink= DELETE_URL+subId+"/users/"+userId;
            if (email!=null){
                SendEmailHTML.sendmail(username, password, email, "Following products are available near you","<h1>You might like the following " +
                        "products available near you </h1>"+ productLink+". To unsubscribe from the service click here "+ unsubscriptionLink);
            }
        }
        return user1;
    }
    @Bean
    public <parseString> double distance(String origins, String destinations, String GOOGLE_DISTANCE_API, String GOOGLE_API_KEY) throws Exception{
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
        String httpsURL = GOOGLE_DISTANCE_API + "?origins="+origins+"&destinations="+destinations+"&key=" + GOOGLE_API_KEY;
        URL myUrl = new URL(httpsURL);
        System.out.println(httpsURL);
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
        //System.out.println("distance="+distance2);
        return distance2;
    }
}