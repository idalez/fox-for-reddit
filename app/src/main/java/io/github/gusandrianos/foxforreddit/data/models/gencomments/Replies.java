package io.github.gusandrianos.foxforreddit.data.models.gencomments;

import com.google.gson.annotations.SerializedName;

public class Replies{

    @SerializedName("data")
    private Data replyData;

    @SerializedName("kind")
    private String kind;

    public Data getreplyData(){
        return replyData;
    }

    public String getKind(){
        return kind;
    }
}