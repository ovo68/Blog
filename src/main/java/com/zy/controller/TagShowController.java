package com.zy.controller;

import com.zy.entity.Tag;
import com.zy.service.BlogService;
import com.zy.service.TagService;
import com.zy.vo.BlogQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {

    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(
                        @PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,
                        Model model){
        List<Tag> tags = tagService.listTagTop(5000);
        if (id==-1){
            id = tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }

}
