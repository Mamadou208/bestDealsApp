package com.example.tb_laota.volleydemo.app;

import com.example.tb_laota.volleydemo.model.dto;

/**
 * Created by Mamadou on 29/12/2015.
 */
public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://192.168.56.1:8080/BestDealsApi/api/users/login/";

    // Server user register url
    public static String URL_REGISTER = "http://192.168.56.1:8080/BestDealsApi/api/users/register";

    public dto.User User;
    private String token;
}
