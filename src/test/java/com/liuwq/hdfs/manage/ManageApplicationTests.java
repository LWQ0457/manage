package com.liuwq.hdfs.manage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManageApplicationTests {

    @Test
    void contextLoads() {
        String path="/";
        System.out.println(path.substring(0,path.lastIndexOf("/")+1));
    }

}
