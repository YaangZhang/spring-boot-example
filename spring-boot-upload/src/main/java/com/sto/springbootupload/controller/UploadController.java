package com.sto.springbootupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private static String UPLOADED_FOLDER = "F://temp//";

    /**
     * 上传单个文件页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "upload";
    }

    /**
     * 上传多个文件页面
     * @return
     */
    @RequestMapping("/more")
    public String uloadMore(){
        return "uploadMore";
    }

     @RequestMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile file,
                                   RedirectAttributes redirectAttributes){
         if (file.isEmpty()) {
             redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
             return "redirect:/uploadStatus";
         }
         try {
             // Get the file and save it somewhere
             byte[] bytes = file.getBytes();
             // UPLOADED_FOLDER 文件本地存储地址
             Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
             File file1 = new File(UPLOADED_FOLDER);
             if (!file1.exists()) {
                file1.mkdir();
             }

             System.out.println(path);
             Files.write(path, bytes);

             redirectAttributes.addFlashAttribute("message",
                     "You successfully uploaded '" + file.getOriginalFilename() + "'");
         } catch (IOException e) {
             e.printStackTrace();
         }
         return "redirect:uploadStatus";
    }

      @RequestMapping("/uploadMore")
    public String uploadMore(@RequestParam("file")MultipartFile[] files,
                                   RedirectAttributes redirectAttributes){
         if (files.length == 0) {
             redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
             return "redirect:/uploadStatus";
         }
          for (MultipartFile file : files) {
              try {
                  // Get the file and save it somewhere
                  byte[] bytes = file.getBytes();
                  // UPLOADED_FOLDER 文件本地存储地址
                  Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                  File file1 = new File(UPLOADED_FOLDER);
                  if (!file1.exists()) {
                      file1.mkdir();
                  }
                  System.out.println(path);
                  Files.write(path, bytes);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          redirectAttributes.addFlashAttribute("message",
                  "You successfully uploaded all");
         return "redirect:uploadStatus";
    }


    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload_status";
    }

}
