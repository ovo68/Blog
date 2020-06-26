package com.zy.controller;

import com.zy.entity.Type;
import com.zy.service.BlogService;
import com.zy.service.TypeService;
import com.zy.vo.BlogQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TypeShowController {

    @Resource
    private TypeService typeService;
    @Resource
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(
                        @PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,
                        Model model){
        List<Type> types = typeService.listTypeTop(5000);
        if (id==-1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        Par par = new Par();
        par.setId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    class Par{
        private Long id;
    }
}
