package com.todaylesson.Sms;


import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class ExampleSend {
  public static void main(String[] args) {
    String api_key = "NCSRC0XSPD85BDRL";
    String api_secret = "2LVQYEMQFBIBEG8WVXKQOWQ6KPDDVJQ9";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", "01026063254");
    params.put("from", "01000000000");
    params.put("type", "SMS");
    params.put("text", "Coolsms Testing Message!");
    params.put("app_version", "test app 1.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
     // System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      //System.out.println(e.getMessage());
      //System.out.println(e.getCode());
    }
  }
}