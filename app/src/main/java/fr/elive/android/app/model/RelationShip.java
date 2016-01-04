package fr.elive.android.app.model;

/**
 * Created by Psyko on 03/01/2016.
 */
public class RelationShip {
    private String entourageForname;
    private String entourageName;
    private Integer relationshipTypeCode;

    public RelationShip(String forname, String name, Integer type){
        this.entourageForname = forname;
        this.entourageName = name;
        this.relationshipTypeCode = type;
    }

    public RelationShip() {

    }

    public String getEntourageForname() {
        return entourageForname;
    }

    public String getEntourageName() {
        return entourageName;
    }

    public String getRelationshipTypeCode() {
        return relationshipTypeCode == 11 ? "Père" : "Mère";
    }

    public void setEntourageForname(String entourageForname) {
        this.entourageForname = entourageForname;
    }

    public void setEntourageName(String entourageName) {
        this.entourageName = entourageName;
    }

    public void setRelationshipTypeCode(Integer relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }

    @Override
    public String toString()
    {
        return "RelationShip [entourageForname=" + entourageForname + ", entourageName=" + entourageName + ", " +
                "relationshipTypeCode=" + this.getRelationshipTypeCode() + "]";
    }
}
