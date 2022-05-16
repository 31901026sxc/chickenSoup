package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ClassDto implements Serializable {
    private final Integer id;
    private final String className;
    private final String classMark;
    private final Set<UserDto> classUserLinks;

    public ClassDto(Integer id, String className, String classMark, Set<UserDto> classUserLinks) {
        this.id = id;
        this.className = className;
        this.classMark = classMark;
        this.classUserLinks = classUserLinks;
    }

    public Integer getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getClassMark() {
        return classMark;
    }

    public Set<UserDto> getClassUserLinks() {
        return classUserLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDto entity = (ClassDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.className, entity.className) &&
                Objects.equals(this.classMark, entity.classMark) &&
                Objects.equals(this.classUserLinks, entity.classUserLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, classMark, classUserLinks);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "className = " + className + ", " +
                "classMark = " + classMark + ", " +
                "classUserLinks = " + classUserLinks + ")";
    }
}
