package io.github.gusandrianos.foxforreddit.data.models.gencomments;

import com.google.gson.annotations.SerializedName;

public class Replies{

    @SerializedName("data")
    private Data data;

    @SerializedName("kind")
    private String kind;

    public Data getReplyData(){
        return data;
    }

    public String getKind(){
        return kind;
    }
}