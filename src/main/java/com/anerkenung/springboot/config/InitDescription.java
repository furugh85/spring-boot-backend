//package com.anerkenung.springboot.config;
//
//import com.anerkenung.springboot.entity.CourseDescriptionEntitiy;
//import com.anerkenung.springboot.repository.DescriptionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.util.List;
//
//@Component
//public class InitDescription implements CommandLineRunner {
//    @Autowired
//    DescriptionRepository descriptionRepository;
//        @Override
//    public void run(String... args) throws Exception {
//        try {
//            List<String> description = Files.readAllLines(java.nio.file.Paths.get("exams.txt"), StandardCharsets.UTF_8);
//
//            for (String line : description) {
//                CourseDescriptionEntitiy courseDescriptionEntitiy1 = new CourseDescriptionEntitiy();
//                String[] tokens = line.split("_");
//
//                courseDescriptionEntitiy1.setExamnumber(tokens[0]);
//                courseDescriptionEntitiy1.setDescription(tokens[1]);
//                descriptionRepository.save(courseDescriptionEntitiy1);
//                System.out.println(descriptionRepository.findByExamnumber("9802"));
//
//                // System.out.println(courseDescriptionEntitiy1.toString());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
