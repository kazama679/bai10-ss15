package com.data.controller;

import com.data.dto.ResumeDTO;
import com.data.model.Resume;
import com.data.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("resume", new ResumeDTO());
        return "add";
    }

    @PostMapping("add")
    public String check(@Valid @ModelAttribute("resume") ResumeDTO resume, BindingResult bindingResult) {
        List<Resume> list = resumeService.findAll();
        if(list.stream().anyMatch(a->a.getEmail().equals(resume.getEmail()))){
            bindingResult.rejectValue("email", null, "Email đã tồn tại!");
        }

        if(list.stream().anyMatch(a->a.getPhoneNumber().equals(resume.getPhoneNumber()))){
            bindingResult.rejectValue("phoneNumber", null, "Số đã tồn tại!");
        }

        if(bindingResult.hasErrors()) {
            return "add";
        }

        Resume resume1 = new Resume();
        resume1.setId(resume.getId());
        resume1.setFullName(resume.getFullName());
        resume1.setEmail(resume.getEmail());
        resume1.setPhoneNumber(resume.getPhoneNumber());
        resume1.setEducation(resume.getEducation());
        resume1.setExperience(resume.getExperience());
        resume1.setSkills(resume.getSkills());
        resumeService.save(resume1);
        return "redirect:/list";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<Resume> resumes = resumeService.findAll();
        model.addAttribute("resumes", resumes);
        return "list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        resumeService.delete(id);
        return "redirect:/list";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Resume resume = resumeService.findById(id);
        if (resume == null) {
            return "redirect:/list";
        }
        ResumeDTO dto = new ResumeDTO();
        dto.setId(resume.getId());
        dto.setFullName(resume.getFullName());
        dto.setEmail(resume.getEmail());
        dto.setPhoneNumber(resume.getPhoneNumber());
        dto.setEducation(resume.getEducation());
        dto.setExperience(resume.getExperience());
        dto.setSkills(resume.getSkills());
        model.addAttribute("resume", dto);
        return "edit";
    }

    @PostMapping("edit")
    public String update(@Valid @ModelAttribute("resume") ResumeDTO resume, BindingResult bindingResult) {
        List<Resume> list = resumeService.findAll();
        if (list.stream().anyMatch(a -> a.getEmail().equals(resume.getEmail()) && a.getId() != resume.getId())) {
            bindingResult.rejectValue("email", null, "Email đã tồn tại!");
        }
        if (list.stream().anyMatch(a -> a.getPhoneNumber().equals(resume.getPhoneNumber()) && a.getId() != resume.getId())) {
            bindingResult.rejectValue("phoneNumber", null, "Số đã tồn tại!");
        }
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        Resume resume1 = new Resume();
        resume1.setId(resume.getId());
        resume1.setFullName(resume.getFullName());
        resume1.setEmail(resume.getEmail());
        resume1.setPhoneNumber(resume.getPhoneNumber());
        resume1.setEducation(resume.getEducation());
        resume1.setExperience(resume.getExperience());
        resume1.setSkills(resume.getSkills());
        resumeService.update(resume1);
        return "redirect:/list";
    }
}
