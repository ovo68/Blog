package com.zy.controller.admin;

import com.zy.entity.Type;
import com.zy.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class TypeController {

    @Resource
    private TypeService service;

    @GetMapping(value = "/types")
    public String list(@PageableDefault(size = 4,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",service.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping(value = "/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type2 = service.getTypeByName(type.getName());
        if (type2!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = service.saveType(type);
        if(type1==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }
    @PostMapping(value = "/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type type2 = service.getTypeByName(type.getName());
        if (type2!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = service.updateType(id,type);
        if(type1==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("type",service.getType(id));
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        service.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
