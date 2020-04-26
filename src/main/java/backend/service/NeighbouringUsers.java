package backend.service;
import backend.model.Seller;
import backend.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class NeighbouringUsers {
    public static User users(User user, Seller seller) throws Exception {
        String userAddr=user.getUserAddr();
        String sellerAddr=seller.getSellerAddr();
        double ss=distance(userAddr,sellerAddr);
        User user1= new User();
        if (ss<5.0){
            BeanUtils.copyProperties(user, user1);
        }
        return user1;
    }

    public static <parseString> double distance(String origins, String destinations) throws Exception{
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
        String httpsURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origins+"&destinations="+destinations+"&key=AIzaSyCsnWpA87MZDsMr9to3aPbZ_WxPBLgZGaU";
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
        System.out.println(distance2);
        return distance2;
    }
}
