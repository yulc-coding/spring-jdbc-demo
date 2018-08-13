package com.ylc.demo.entity;

/**
 * @Description : 系统字典表
 * @Author : yulc
 * @Date : 2018/8/12 13:55
 */
public class Dict {

    private Integer id;

    private String TypeCode;

    private String typeName;

    private String dictCode;

    private String dictName;

    private Integer pid;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "id=" + id +
                ", TypeCode='" + TypeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictName='" + dictName + '\'' +
                ", pid=" + pid +
                ", status=" + status +
                '}';
    }
}
