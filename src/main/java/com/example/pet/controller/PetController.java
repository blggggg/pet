package com.example.pet.controller;

import com.example.petdemo.model.Pet;
import com.example.pet.service.PetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @Value("${pet.upload-dir:uploads}")
    private String uploadDir;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Pet> pets = petService.list();
        model.addAttribute("pets", pets);
        return "pet_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Pet pet = petService.getById(id);
        model.addAttribute("pet", pet);
        return "pet_detail";
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("formAction", "/pet/save");
        return "pet_form";
    }

    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable Long id, Model model) {
        Pet pet = petService.getById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("formAction", "/pet/save");
        return "pet_form";
    }

    @GetMapping("/toReport/{id}")
    public String toReport(@PathVariable Long id, Model model, HttpServletResponse response) {
        Pet pet = petService.getById(id);
        petService.generateReport(id,  pet,  response);
        model.addAttribute("pet", pet);
        model.addAttribute("formAction", "/pet/save");
        return "pet_form";}

    @PostMapping("/save")
    public String save(@ModelAttribute Pet pet,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        String uploadedUrl = saveImageIfPresent(imageFile);
        if (uploadedUrl != null) {
            pet.setImageUrl(uploadedUrl);
        } else if (!StringUtils.hasText(pet.getImageUrl())) {
            pet.setImageUrl("/images/default-pet.png");
        }

        if (pet.getId() == null) {
            petService.save(pet);
        } else {
            Pet old = petService.getById(pet.getId());
            if (old != null && !StringUtils.hasText(uploadedUrl) && StringUtils.hasText(old.getImageUrl())) {
                pet.setImageUrl(old.getImageUrl());
            }
            petService.update(pet);
        }
        return "redirect:/pet/list";
    }

    private String saveImageIfPresent(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        String originalFilename = imageFile.getOriginalFilename();
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String fileName = UUID.randomUUID() + ext;
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        Path target = uploadPath.resolve(fileName);
        Files.copy(imageFile.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + fileName;
    }
}

/*
If your project does not already expose local uploaded files, add this config class:

@Configuration
public class WebResourceConfig implements WebMvcConfigurer {
    @Value("${pet.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = Paths.get(uploadDir).toAbsolutePath().normalize().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}
*/
