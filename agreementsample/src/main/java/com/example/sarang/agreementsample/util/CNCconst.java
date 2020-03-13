package com.example.sarang.agreementsample.util;


/**
 * Created by erise on 2016. 6. 17..
 */
public class CNCconst {

    public static final String SENDER_ID = "969468588059"; //GCM project number
    public static final String CNC_URL = "http://dev.erise.co.kr";
    public static final String CNC_URL_REAL = "http://192.168.200.44:8080/cnc";
    public static final String CNC_TEST_URL = "http://dev.erise.co.kr";
    public static final String CNC_COOKIE = "www.ontongsotong.kr";
    //public static final String MOBILE_HOME = "http://www....";
    public static final String DECODE_KEY = "fkeir9385laic97a6s9j0n8v7wjf79a0";

    public static String BEAUTYTOKEN = "";
    public static String BEAUTYT_USERID = "";
    public static final String BEAUTY_URL = "http://dev.erise.co.kr/beautyon";
    public static final String BEAUTY_URL_REAL = "www.rabeautyon.co.kr";

    public static final boolean DEBUGMODE = true;

    public static int Stats = 0;  // 0 - parent  || 1 - studunts || 2 - teacher

    public static int ChildCount = 0;

    public static int NowViewPage = 0;

    //public static String ViewPicName = "";

    public static boolean NEW_PUSH = false;

    public static String OsType = "Android";

    public static double default_latitude = 37.498709; // 강남역 11번 출구
    public static double default_logitude = 127.027454;

    public static double latitude = 37.498709;
    public static double logitude = 127.027454;

    public static boolean IsGetGPS = false;
    public static boolean IsGPSChangeMain = false;
    public static boolean IsGPSChangeNear = false;
}
