package com.its.ex.test;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class testClass {
    @Autowired //test 에서는 @Autowired 사용 허가한다.
    private TestService testService;

    // testService의 save() 호출
    // 호출후 리턴값을 print
    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest(){
        /**
         * 1.저장할 TestDTO 객체를 만들고 필드값을 저장.
         * 2. DTO 객체를 서비스의 save 메서드로 전달
         * 3. 전달 후 리턴 값을 받아서(Long)
         * 4. 그 리턴값으로  DB에서 findById를 하고
         * 5. DB에서 조회된 데이터와 1.번에서 저장한 데이터가 일치하는지를 판단하여
         * 6. 일치하면 테스트 통과, 일치하지 않으면 테스트 실패
         */
        // 1.
        TestDTO testDTO = new TestDTO("테스트데이터1","테스트데이터2");
        // 2. 3.
        Long saveId = testService.save(testDTO);
        // 4.
        TestDTO findDTO = testService.findById(saveId);
        // 5.6.
        assertThat(testDTO.getColumn1()).isEqualTo(findDTO.getColumn1()); // 코딩테스트
    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("findAll 테스트")
    public void findAllTest(){
        /**
         * 1. 3개의 테스트 데이터 저장
         * 2. findAll 호출
         * 3. 호출한 리스트의 크기가 3인지를 판다.
         * 4. 3이면 테스트 통과, 아니면 테스트 실패
         */
        // 3개의 테스트 데이터를 저장해봅시다. 반복문을 써서
        TestDTO testDTO;
        for(int i=1; i<=3; i++){
//            testDTO = new TestDTO("테스트데이터" +1,"테스트데이터"+i);
//            testService.save(testDTO);
//            testService.save(new TestDTO("테스트데이터" +i, "테스트데이터"+i));
        }

        // 자바 람다식(화살표함수),IntStream
        IntStream.rangeClosed(1,3).forEach(i -> {
            testService.save(new TestDTO("테스트데이터" +i,"테스트데이터"+i));
        });

        // findAll 호출해서 리스트 크기가 3과 일치하는지 확인해봅시다.
        List<TestDTO>testDTOList = testService.findAll();
        assertThat(testDTOList.size()).isEqualTo(3); //체이닝 메서드
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("삭제 테스트")
    public void deleteTeTest(){
        // 1. 삭제할 대상을 저장
        TestDTO testDTO = new TestDTO("테스트데이터1","테스트데이터2");
        // 2. 3. 저장될 아이디값을 가져온다
        Long saveId = testService.save(testDTO);
        // 4. 삭제수행
        testService.delete(saveId);
        assertThat(testService.findById(saveId)).isNull();
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("수정 테스트")
    public void updateTest(){
      /**
       * 수정 테스트를 어떻게 할지 시나리오 써보시고.
       * assertThat().isNotEqualTo()쓰면 됩니다.
       * 1. 새로운 데이터 저장
       * 2. 저장한 객체를 조회하여 test_column1의 값을 가지고 있음.
       * 3. test_column1의 값을 변경함.
       * 4. 수정처리
       * 5. umn1의수정 후 해당 객체를 조회하여 test_col 값을 가져옴.
       * 6. 2번에서 조회한 값과 5번에서 조회한 값이 같은지를 비교하여(각각의 test_column1)
       * 7. 다르면 테스트 성공, 같아면 테스트 실패
       */
      //1, 새로운 데이터 저장
      TestDTO saveDTO = new TestDTO("수정데이터1", "수정데이터2");
      Long saveId = testService.save(saveDTO);
      //2. 저장한 객체를 조회하여 test_column1의 값을 가지고 있음.
      TestDTO findDTO = testService.findById(saveId);
      //3. test_column1의 값을 변경함
      TestDTO updateDTO = new TestDTO(saveId,"변경데이터1","수정데이터");
      //4, 수정처리
      testService.update(updateDTO);
      //5. umn1의수정 후 해당 객체를 조회하여 test_col 값을 가져옴.
      TestDTO afterUpdateDTO = testService.findById(saveId);
      //6.7   2번에서 조회한 값과 5번에서 조회한 값이 같은지를 비교하여(각각의 test_column1) , 다르면 테스트 성공, 같아면 테스트 실패
      assertThat(findDTO.getColumn1()).isNotEqualTo(afterUpdateDTO.getColumn1());
  }
}


