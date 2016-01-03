package fr.elive.android.app.model;

/**
 * Created by chriis on 03/01/2016.
 */
public class Tweet {

    private String titre;
    private String description;

    public Tweet(String titre, String description) {

        this.titre = titre;
        this.description = description;
    }



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}