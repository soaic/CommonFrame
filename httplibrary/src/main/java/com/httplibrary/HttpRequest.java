package com.httplibrary;

/**
 * Created by soaic on 2016/10/23.
 */

public class HttpRequest {

    private static class SingleLoader{
        private static final HttpRequest INSTANCE = new HttpRequest();
    }

    public static HttpRequest getInstance(){
        return SingleLoader.INSTANCE;
    }


    

}
