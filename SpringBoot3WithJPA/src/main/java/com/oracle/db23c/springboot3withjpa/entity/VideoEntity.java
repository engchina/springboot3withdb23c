package com.oracle.db23c.springboot3withjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// @Entity是 JPA 的注解，用于指示这是 JPA 管理的类型。
@Entity
public class VideoEntity {
    // @Id是 JPA 标记主键的注解。
    // @GeneratedValue 是一个 JPA 注解，用于将key的生成卸载到 JPA 提供程序。
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String description;

    // JPA 需要一个公共或受保护的无参数构造函数方法。
    public VideoEntity() {
        this(null, null);
    }

    // 我们还有一个未提供 id 字段的构造函数：一个专为在数据库中创建新条目而设计的构造函数。
    // 当 id 字段为 null 时，它会告诉 JPA 我们要在表中创建一个新行。
    public VideoEntity(String name, String description) {
        this.id = null;
        this.description = description;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
