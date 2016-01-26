/* Options:
Date: 2015-11-24 21:28:59
Version: 4,046
BaseUrl: http://localhost:6958

Package: com.infinibits.paylist.models
GlobalNamespace: dto
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion: 
//IncludeTypes: 
//ExcludeTypes: 
//TreatTypesAsStrings: 
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*,com.google.gson.annotations.*,com.google.gson.reflect.*
*/

package com.example.tb_laota.BestDeals.model;

import com.google.gson.reflect.TypeToken;

import net.servicestack.client.*;

import org.parceler.Parcel;

import java.util.ArrayList;

public class dto
{

    @Route(Path="/authentication", Verbs="POST")
    // @ApiResponse(200, "Request was handled successfully")
    // @ApiResponse(409, "A logical error occurred")
    // @ApiResponse(500, "Error occurred on the service")
    public static class Login implements IReturn<LoginResponse>
    {
        public String Name = null;
        public String Password = null;
        
        public String getName() { return Name; }
        public Login setName(String value) { this.Name = value; return this; }
        public String getPassword() { return Password; }
        public Login setPassword(String value) { this.Password = value; return this; }
        private static Object responseType = LoginResponse.class;
        public Object getResponseType() { return responseType; }
    }


    @Route(Path="/registration", Verbs="POST")
    // @ApiResponse(200, "Request was handled successfully")
    // @ApiResponse(409, "A logical error occurred")
    // @ApiResponse(500, "Error occurred on the service")
    public static class Register implements IReturn<LoginResponse>
    {
        public String Name = null;
        public String Password = null;
        public String FirstName = null;
        public String LastName = null;
        
        public String getName() { return Name; }
        public Register setName(String value) { this.Name = value; return this; }
        public String getPassword() { return Password; }
        public Register setPassword(String value) { this.Password = value; return this; }
        private static Object responseType = LoginResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/users", Verbs="GET")
    // @Route(Path="/users/{Id}", Verbs="GET")
    // @Route(Path="/users/name/{Name}", Verbs="GET")
    // @ApiResponse(200, "Request was handled successfully")
    // @ApiResponse(500, "Error occurred on the service")
    public static class SearchUser implements IReturn<ArrayList<User>>
    {
        public Integer Id = null;
        public String Name = null;
        
        public Integer getId() { return Id; }
        public SearchUser setId(Integer value) { this.Id = value; return this; }
        public String getName() { return Name; }
        public SearchUser setName(String value) { this.Name = value; return this; }
        private static Object responseType = new TypeToken<ArrayList<User>>(){}.getType();
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/users", Verbs="POST")
    public static class SaveUser implements IReturn<User>
    {
        public User User = null;
        
        public User getUser() { return User; }
        public SaveUser setUser(User value) { this.User = value; return this; }
        private static Object responseType = User.class;
        public Object getResponseType() { return responseType; }
    }

    @Parcel
    public static class LoginResponse
    {
        public User User = null;
        public String Token = null;
        
        public User getUser() { return User; }
        public LoginResponse setUser(User value) { this.User = value; return this; }
        public String getToken() { return Token; }
        public LoginResponse setToken(String value) { this.Token = value; return this; }
    }

    @Parcel
    public static class User {
        public Integer Id = null;
        public String Name = null;
        public String Password = null;
        public String FirstName = null;
        public String LastName = null;
        public ArrayList<UserGroup> Groups = null;

        public Integer getId() {
            return Id;
        }

        public User setId(Integer value) {
            this.Id = value;
            return this;
        }

        public String getName() {
            return Name;
        }

        public User setName(String value) {
            this.Name = value;
            return this;
        }

        public String getPassword() {
            return Password;
        }

        public User setPassword(String value) {
            this.Password = value;
            return this;
        }

        public String getFirstName() {
            return FirstName;
        }

        public User setFirstName(String value) {
            this.FirstName = value;
            return this;
        }

        public String getLastName() {
            return LastName;
        }

        public User setLastName(String value) {
            this.LastName = value;
            return this;
        }

        public ArrayList<UserGroup> getGroups() {
            return Groups;
        }

        public User setGroups(ArrayList<UserGroup> value) {
            this.Groups = value;
            return this;
        }
    }


    @Parcel
    public static class UserGroup
    {
        public Integer GroupId = null;
        public String Name = null;
        
        public Integer getGroupId() { return GroupId; }
        public UserGroup setGroupId(Integer value) { this.GroupId = value; return this; }
        public String getName() { return Name; }
        public UserGroup setName(String value) { this.Name = value; return this; }
    }

}
