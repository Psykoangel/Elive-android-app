package fr.elive.android.app.model;

/**
 * Created by Psyko on 03/01/2016.
 */
public class CmaObject {
    private String cmaCode1;
    private String cmaCode2;
    private String cmaLevel;
    private String cmaValue;

    public String getCmaCode1() {
        return cmaCode1;
    }

    public void setCmaCode1(String cmaCode1) {
        this.cmaCode1 = cmaCode1;
    }

    public String getCmaCode2() {
        return cmaCode2;
    }

    public void setCmaCode2(String cmaCode2) {
        this.cmaCode2 = cmaCode2;
    }

    public String getCmaLevel() {
        return cmaLevel;
    }

    public void setCmaLevel(String cmaLevel) {
        this.cmaLevel = cmaLevel;
    }

    public String getCmaValue() {
        return cmaValue;
    }

    public void setCmaValue(String cmaValue) {
        this.cmaValue = cmaValue;
    }

    @Override
    public String toString() {
        return "CmaObject [cmaCode1=" + cmaCode1 + ", cmaCode2=" + cmaCode2 + ", " +
                "cmaLevel=" + cmaLevel + ", cmaValue=" + cmaValue + "]";
    }
}
