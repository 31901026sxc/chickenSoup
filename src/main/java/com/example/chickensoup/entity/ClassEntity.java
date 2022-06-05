package com.example.chickensoup.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "class")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_mark")
    private String classMark;

    @OneToMany(mappedBy = "_class")
    private Set<ClassUserLinkEntity> classUserLinks = new LinkedHashSet<>();

    public Set<ClassUserLinkEntity> getClassUserLinks() {
        return classUserLinks;
    }

    public void setClassUserLinks(Set<ClassUserLinkEntity> classUserLinks) {
        this.classUserLinks = classUserLinks;
    }

    public String getClassMark() {
        return classMark;
    }

    public void setClassMark(String classMark) {
        this.classMark = classMark;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}