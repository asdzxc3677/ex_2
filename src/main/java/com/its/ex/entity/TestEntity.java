package com.its.ex.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "test_table") //테이블 이름 변경하고 싶을때
public class TestEntity { //테이블
    @Id //pk역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "test_id") // 컬럼 이름 지정
    private Long id;

    @Column(name = "test_column1", length = 50) //컬럼역할 length = 50 <-- 이렇게도 설정가능
    private String column1; //컬럼

    @Column(unique = true) // 유니크라는 제약조건을 추가
    private String testColumn;
}
