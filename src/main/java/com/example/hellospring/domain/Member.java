package com.example.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 Entity
public class Member {

    // db가 id를 자동으로 생성해주는 것을 identity 전략?이라한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
}
