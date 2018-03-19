package com.phgarcia.socialactions.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialEntity {
    @Expose @SerializedName("id") private long id;
    @Expose @SerializedName("nome") private String name;
    @Expose @SerializedName("imagem") private String coverImageURL;
    @Expose @SerializedName("descricao") private String description;
    @Expose @SerializedName("site") private String website;

    public long getId() { return id; }
    public String getName() { return name; }
    public String getCoverImageURL() { return coverImageURL; }
    public String getDescription() { return description; }
    public String getWebsite() { return website; }
}
