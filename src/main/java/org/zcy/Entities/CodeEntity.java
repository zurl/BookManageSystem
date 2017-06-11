package org.zcy.Entities;

/**
 * Created by zcy on 2017/3/28.
 */
public class CodeEntity {

    private Integer code;

    public CodeEntity(Integer code) throws Exception{
        if(code == -1) throw new Exception("failed");
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
