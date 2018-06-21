package com.mikegu.tools.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 处理URL的encode, decode
 */
public class URLHandler {

    public static String decode(String url) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, "UTF-8");
    }

    public static String encode(String url, String encoding) throws UnsupportedEncodingException {
        return URLEncoder.encode(url,encoding) ;
    }



    public  static  void main(String [] args) throws UnsupportedEncodingException {

        //解码
        String encodeUrl = "http://rpbmanager.51.nb/report/refundLineQuery?pageSize=50&pageNumber=1&date=2018-03-21+00%3A00%3A00+-+2018-03-22+00%3A00%3A00&sinaState=&refundState=&transferChannel=&platform=&userId=&refundNo=";
        System.out.println(decode(encodeUrl));

        //加码
        String originalUrl = "http://rpbmanager.51.nb/report/refundLineQuery?pageSize=50&pageNumber=1&date=2018-03-21 00:00:00 - 2018-03-22 00:00:00&sinaState=&refundState=&transferChannel=&platform=&userId=&refundNo=";
        System.out.println(encode(originalUrl, "UTF-8"));

    }

}
