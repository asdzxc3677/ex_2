package com.its.ex.dto;

import com.its.ex.entity.TestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Test;

@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor //모든매게변수가 잇는 생성자
public class TestDTO {
        private Long id;
        private String column1;
        private String testColumn2;

    public TestDTO(String column1, String testColumn2) {
        this.column1 = column1;
        this.testColumn2 = testColumn2;
    }

    public static TestDTO toDTO(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setColumn1(testEntity.getColumn1());
        testDTO.setTestColumn2(testEntity.getTestColumn2());
        return testDTO;
    }
}

