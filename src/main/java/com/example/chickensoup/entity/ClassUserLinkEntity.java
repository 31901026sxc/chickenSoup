package com.example.chickensoup.entity;

import javax.persistence.*;

@Entity
@Table(name = "class_user_link")
public class ClassUserLinkEntity {
    @Id
    @Column(name = "link_id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity _class;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    ClassUserLinkEntity

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ClassEntity get_class() {
        return _class;
    }

    public void set_class(ClassEntity _class) {
        this._class = _class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}