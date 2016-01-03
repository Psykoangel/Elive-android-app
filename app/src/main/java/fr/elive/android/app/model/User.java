package fr.elive.android.app.model;

import java.util.List;

/**
 * Created by Psyko on 03/01/2016.
 */
public class User {
    private Integer id;
    private String userForname;
    private String userName;
    private List<RelationShip> relationshipList;
    private List<CmaObject> userCmaList;

    public User(){
    }

    public User(String userForname, String userName){
        this.id = 0;
        this.userForname = userForname;
        this.userName = userName;
    }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getUserForname()
    {
        return userForname;
    }
    public void setUserForname(String userForname)
    {
        this.userForname = userForname;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public List<RelationShip> getRelationshipList()
    {
        return relationshipList;
    }
    public void setRelationshipList(List<RelationShip> relationshipList)
    {
        this.relationshipList = relationshipList;
    }

    public List<CmaObject> getUserCmaList() {
        return userCmaList;
    }

    public void setUserCmaList(List<CmaObject> userCmaList) {
        this.userCmaList = userCmaList;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", userForname=" + userForname + ", " +
                "userName=" + userName + ", roles= [" + relationshipList + "], cma=[" + userCmaList + "]]";
    }
}
