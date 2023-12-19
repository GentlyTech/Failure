package com.yepdevelopment.failure.Database.Entities;

/**
 * This is not a database entity. I just wasn't sure where else to put it.
 */
public class Contributor {
    private final String name;
    private final String imageUri;

    public Contributor(String name) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }

        this.imageUri = "";
    }

    public Contributor(String name, String imageUri) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }

        if (imageUri == null) {
            this.imageUri = "";
        } else {
            this.imageUri = imageUri;
        }
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }
}
