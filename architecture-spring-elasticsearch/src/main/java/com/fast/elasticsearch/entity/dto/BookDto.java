package com.fast.elasticsearch.entity.dto;


import java.io.Serializable;

public class BookDto implements Serializable {

    /**
     * Id
     */
    private Long id;

    /**
     * 名称
     */

    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 价格
     */

    private long price;


    /**
     * 空构造方法
     */
    public BookDto() {
    }

    /**
     * 构造赋值方法
     */
    public BookDto(Long id, String name, String desc, Long price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
