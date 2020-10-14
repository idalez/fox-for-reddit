package io.github.gusandrianos.foxforreddit.data.models.gencomments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildrenItem{

    @Expose
    @SerializedName("data")
    private Data data;

    @SerializedName("kind")
    private String kind;

    private String simpleData;

    public String getSimpleData() {
        return simpleData;
    }

    public Data getData(){
        return data;
    }

    public String getKind(){
        return kind;
    }
}