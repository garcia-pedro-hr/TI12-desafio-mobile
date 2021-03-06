package com.phgarcia.desafioandroid.model;

import java.io.Serializable;

/**
 * Created by garci on 12/19/2017.
 */

public class Deed implements Serializable{

    private Long id;
    private String name;
    private String imageURL;
    private String imagePath;
    private String description;
    private String site;

    public Deed () {}

    public Deed (Long id, String name, String imageURL, String description, String site) {
        this.setId(id);
        this.setName(name);
        this.setImagePath(imageURL);
        this.setDescription(description);
        this.setSite(site);
    }

    public Long     getId()           { return id; }
    public String   getName()         { return name; }
    public String   getImageURL()     { return imageURL; }
    public String   getImagePath()    { return imagePath; }
    public String   getDescription()  { return description; }
    public String   getSite()         { return site; }

    public void setId(Long id)                      { this.id = id; }
    public void setName(String name)                { this.name = name; }
    public void setImageURL(String imageURL)        { this.imageURL = imageURL; }
    public void setImagePath(String imagePath)      { this.imagePath = imagePath; }
    public void setDescription(String description)  { this.description = description; }
    public void setSite(String site)                { this.site = site; }

    public String toString()  { return this.name; }
}
