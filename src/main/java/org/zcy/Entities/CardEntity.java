package org.zcy.Entities;

/**
 * Created by zcy on 2017/3/1.
 */
public class CardEntity {

    private Integer cardid;
    private String name;
    private String unit;
    private String type;

    public CardEntity(Integer cardid, String name, String unit, String type) {
        this.cardid = cardid;
        this.name = name;
        this.unit = unit;
        this.type = type;
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
