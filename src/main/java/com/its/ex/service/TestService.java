package com.its.ex.service;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Long save(TestDTO testDTO) {
        System.out.println("testDTO" + testDTO);
        //TestDTO 객체에 담긴 값을 TestEntity 객체에 옮겨 담기
        TestEntity testEntity = TestEntity.toEntity(testDTO);
        Long id = testRepository.save(testEntity).getId(); // save 한 entity를 리턴
        return id;
    }
}
