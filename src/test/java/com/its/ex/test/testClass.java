package com.its.ex.test;

import com.its.ex.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testClass {
    @Autowired //test 에서는 @Autowired 사용 허가한다.
    private TestService testService;

    // testService의 save() 호출
    // 호출후 리턴값을 print
    @Test
    public void saveTest(){
        //Long testResult = testService.save();
        // System.out.println("testResult = " + testResult);
    }
}
