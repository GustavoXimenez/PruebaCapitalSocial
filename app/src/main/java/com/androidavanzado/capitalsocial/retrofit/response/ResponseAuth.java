
package com.androidavanzado.capitalsocial.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAuth() {
    }

    /**
     * 
     * @param id
     * @param token
     */
    public ResponseAuth(Integer id, String token) {
        super();
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
