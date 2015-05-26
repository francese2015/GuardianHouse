package it.unisa.guardianhouse.config;

public class Config {

    public static String BASE_URL = "http://carlo.teammolise.rocks/api";
    //public static String BASE_URL = "10.0.2.2/guardianhouse-webservices";

    public static String LOGIN_URL = BASE_URL + "/login";

    public static String REGISTER_URL = BASE_URL + "/register";

    public static String USERS_URL = BASE_URL + "/users";

    public static String APARTMENTS_URL = BASE_URL + "/apartments";

    public static String SEARCH_APT_URL = BASE_URL + "/apartments/search";

    public static String FEATURED_APT_URL = BASE_URL + "/apartments/featured";

    public static int MAX_SEARCH_RADIUS = 20;

}
