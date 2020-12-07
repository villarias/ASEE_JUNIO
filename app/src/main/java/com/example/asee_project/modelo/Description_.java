
package com.example.asee_project.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description_ {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private String text;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
