package com.ideamart.sample.lbs;


import com.google.gson.*;
import com.ideamart.sample.common.Constants;
import com.ideamart.sample.subcription.Subscription;
import com.ideamart.sample.usermgt.UserDAO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URI;
import java.util.Scanner;

/**
 * Created by tharinda on 10/21/16.
 */
public class LBS {

    private String APIkey = "AIzaSyDfPfRnA7qzQe5Qhg9SQkNxhGVryguhN3w";

    public ResponseBean getLatLonByAddress(String address) throws IOException {
        LBSbean lbSbean = new LBSbean();
        lbSbean.setApplicationId(Constants.ApplicationConstants.APP_ID);
        lbSbean.setPassword(Constants.ApplicationConstants.PASSWORD);
        lbSbean.setSubscriberId(address);
        lbSbean.setFreshness(Constants.ApplicationConstants.LBS_FRESHNESS);
        lbSbean.setHorizontalAccuracy(Constants.ApplicationConstants.LBS_ACCURACY);
        lbSbean.setResponseTime(Constants.ApplicationConstants.LBS_DELAY);
        lbSbean.setServiceType(Constants.ApplicationConstants.LBS_SERVICE_TYPE);
        lbSbean.setVersion(Constants.ApplicationConstants.LBS_DELAY);
        String postUrl = Constants.ApplicationConstants.LBS_URL;
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(lbSbean));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine());
        InputStream inputStream = response.getEntity().getContent();
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        JsonElement jelement = new JsonParser().parse(result);
        JsonObject jobject = jelement.getAsJsonObject();
        String lat = String.valueOf(jobject.get("latitude")).replaceAll("['\"]", "");
        String lon = String.valueOf(jobject.get("longitude")).replaceAll("['\"]", "");
        ResponseBean responseBean = new ResponseBean();
        responseBean.setLatitude(lat);
        responseBean.setLongitude(lon);
        return responseBean;
    }

    public String getLocation(String address) throws Exception {
        ResponseBean responseBean;
        responseBean = getLatLonByAddress(address);
        return getMapLocation(responseBean.getLatitude(), responseBean.getLongitude());

    }

    public String getMapLocation(String lat, String lon) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lon + "&key=" + APIkey;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet();
        get.setURI(URI.create(url));
        HttpResponse response = httpClient.execute(get);
        System.out.println(response.getStatusLine());
        InputStream inputStream = response.getEntity().getContent();
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        System.out.println("Google result");
        System.out.println(result);
        JsonElement jelement = new JsonParser().parse(result);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray array = jobject.getAsJsonArray("results");
        String address = array.get(0).getAsJsonObject().get("formatted_address").toString().replaceAll("['\"]", "");
        String mapURL = "http://maps.google.com/?ll=" + lat + "," + lon;
        return address + "\n" + mapURL;

    }

    public ResponseBean getLatLonByPin(String pin) throws IOException {
        ResponseBean responseBean = new ResponseBean();
        Subscription subscription = new Subscription();
        try {
            UserDAO userDAO = new UserDAO();
            String address = userDAO.getUserAddressByPin(pin);
            if (address.equals("null")) {
                responseBean.setStatus("fail");
            } else if (!subscription.getStatus(address)) {
                responseBean.setStatus("fail");
            } else {
                responseBean = getLatLonByAddress(address);
                responseBean.setStatus("success");
            }
            return responseBean;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
