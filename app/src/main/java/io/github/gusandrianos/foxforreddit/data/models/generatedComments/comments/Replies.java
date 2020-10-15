package io.github.gusandrianos.foxforreddit.data.models.generatedComments.comments;

import com.google.gson.annotations.SerializedName;

public class Replies{

    @SerializedName("data")
    private Data data;

    @SerializedName("kind")
    private String kind;

    private String name;

    public Replies(String name) {
        this.name = name;
    }

    public Data getData(){
        return data;
    }

    public String getKind(){
        return kind;
    }
}