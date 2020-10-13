package io.github.gusandrianos.foxforreddit.data.models.genpost;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("data")
    private Data data;

    @SerializedName("kind")
    private String kind;

    public Data getData(){
        return data;
    }

    public String getKind(){
        return kind;
    }
}