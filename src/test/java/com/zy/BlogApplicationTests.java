package com.zy;

import com.zy.entity.Tag;
import com.zy.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    TagService service;
    @Test
    void contextLoads() {
//        service.saveTag(new Tag(1,"实战"));
//        Tag tag = service.getTagByName("实战");
//        Tag tag = service.updateTag(13,new Tag(13,"测试"));
//        Tag tag = service.getTag(14);
//        service.deleteTag(13);
//        System.out.println(tag.getName());
    }

}
