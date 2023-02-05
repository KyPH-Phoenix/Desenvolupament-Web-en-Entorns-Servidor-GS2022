package com.liceu.objects.controller;

import com.liceu.objects.exception.ObjectAlreadyExistsException;
import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketObject;
import com.liceu.objects.model.User;
import com.liceu.objects.service.BucketService;
import com.liceu.objects.util.Utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BucketController {
    @Autowired
    BucketService bucketService;

    @Autowired
    HttpSession session;

    @GetMapping("/objects")
    public String objectsGet(Model model) {

        User user = (User) session.getAttribute("user");

        List<Bucket> buckets = bucketService.getBucketsFromUser(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("buckets", buckets);

        return "userpage";
    }

    @PostMapping("/objects")
    public RedirectView objectsPost(String bucketname) {
        String username = ((User) session.getAttribute("user")).getUsername();

        bucketService.createBucket(bucketname, username);

        return new RedirectView("/objects");
    }

    @GetMapping("/objects/{bucketname}")
    public String bucketGet(@PathVariable String bucketname, Model model) {
        List<BucketObject> objects = bucketService.getAllObjectsFromBucket(bucketname);

        List<String> directories = new ArrayList<>();
        List<String> files = new ArrayList<>();

        objects.forEach(object -> {
            String name = object.getObjectname();
            String[] segments = name.split("/");

            if (segments.length > 1) {
                if (!directories.contains(segments[1])) {
                    directories.add(segments[1]);
                }
            } else {
                files.add(name);
            }
        });

        model.addAttribute("directories", directories.stream().sorted().collect(Collectors.toList()));
        model.addAttribute("files", files.stream().sorted().collect(Collectors.toList()));
        model.addAttribute("bucketname", bucketname);

        return "bucket";
    }

    @PostMapping("/objects/{bucketname}")
    public RedirectView bucketPost(@PathVariable String bucketname, MultipartFile file, String path) {
        User user = (User) session.getAttribute("user");

        try {
            bucketService.createObject(file, path, bucketname, user);
        } catch (IOException E) {
            System.out.println("ERROR");
            return new RedirectView("/objects/{bucketname}");
        }

        return new RedirectView("/objects/{bucketname}");
    }

    @GetMapping("objects/{bucketname}/**")
    public RedirectView getObject(@PathVariable String bucketname, HttpServletRequest req) {
        String url = (String) req.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE
        );

        System.out.println(url);

        return new RedirectView("/objects/" + bucketname);
    }


    /*
    @PostMapping("...")
    public ResponseEntity<byte[]> download() {
        byte[] content = file.getContent();
        String name = obj.getName();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/octet-stream"));
        headers.setContentLength(content.length);
        headers.setContentDisposition(
                ContentDisposition.parse("attachment; filename=" + name)
        );

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
    */
}
