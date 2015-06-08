package net.aethylic;

/**
 * Created by user on 23.01.2015.
 */


/*
*
* */


/*

 */

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by user on 06.06.2015.
 */
public class vk_get_photo {
    static final String ACCESS_TOKEN = "";
    static final String GROUP_DOMAIN = "";
    static final String OWNER_ID = "";
    //private String USER_ID;
    private URIBuilder uriBuilder = new URIBuilder();

    public vk_get_photo() {
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/photos.search")
                //.setParameter("user_id", USER_ID)
                /*.setParameter("order", "name")
                .setParameter("count", "1000")
                .setParameter("offset", "0")
                .setParameter("fields", "photo_50,photo_max_orig")*/
                .setParameter("lat", "30")
                .setParameter("long", "30")
                //.setParameter("radius", "5000")
                .setParameter("count", "1000")
                .setParameter("offset", "0");
                //.setParameter("start_time", "0")
                //.setParameter("end_time", "0")
                //.setParameter("access_token", ACCESS_TOKEN);


    }

    public JSONArray get(Integer radius) {
        //this.USER_ID = uid.toString();
        JSONArray items=null;
        this.uriBuilder.setParameter("radius", radius.toString());
        HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
        Integer status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            StringWriter content = new StringWriter();

            try {
                IOUtils.copy(response.getEntity().getContent(), content);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            JSONParser parser = new JSONParser();

            try {

                JSONObject jsonResp = (JSONObject) parser.parse(content.toString());
                items = (JSONArray)(((JSONObject)jsonResp.get("response")).get("items"));
                //MaltegoMessage maltego = new MaltegoMessage();
                /*for (int i=(items.size()-1); i > 0 ; i--) {

                    //maltego.MessageEntiti((JSONObject)items.get(i));
                    System.out.println("<id>=" + items.get(i).toString() + "</id>");
                }*/
                //maltego.test();
                //maltego.MessageEnd();

            } catch (ParseException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return items;

    }
}



