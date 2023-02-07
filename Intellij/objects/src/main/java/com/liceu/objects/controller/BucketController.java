package com.liceu.objects.controller;

import com.liceu.objects.model.*;
import com.liceu.objects.service.BucketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
        if (bucketNotFromUser(bucketname)) return "error";

        List<BucketObject> objects = bucketService.getAllObjectsFromBucket(bucketname);

        List<String> directories = new ArrayList<>();
        List<String> files = new ArrayList<>();

        objects.forEach(object -> {
            String name = object.getObjectname().substring(1);
            String[] segments = name.split("/");

            if (segments.length > 1) {
                if (!directories.contains(segments[0])) {
                    directories.add(segments[0]);
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

    private boolean bucketNotFromUser(String bucketname) {
        List<Bucket> buckets = bucketService.getBucketsFromUser((User) session.getAttribute("user"));

        return buckets.stream().noneMatch(bucket -> bucket.getBucketname().equals(bucketname));
    }

    @PostMapping("/objects/{bucketname}/**")
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

    @GetMapping("/objects/{bucketname}/**")
    public String getObject(@PathVariable String bucketname, HttpServletRequest req, Model model) {
        String url = (String) req.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE
        );

        url = url.replace("%20", " ");

        if (url.charAt(url.length() - 1) == '/') {
            String directoryName = url.substring(9 + bucketname.length());

            List<BucketObject> objects = bucketService.getAllObjectsFromDirectory(directoryName, bucketname);

            List<String> directories = new ArrayList<>();
            List<String> files = new ArrayList<>();

            objects.forEach(object -> {
                String name = object.getObjectname().substring(directoryName.length());
                String[] segments = name.split("/");

                if (segments.length > 1) {
                    if (!directories.contains(segments[0])) {
                        directories.add(segments[0]);
                    }
                } else {
                    files.add(name);
                }
            });

            model.addAttribute("directories", directories.stream().sorted().collect(Collectors.toList()));
            model.addAttribute("files", files.stream().sorted().collect(Collectors.toList()));
            model.addAttribute("bucketname", bucketname + directoryName.substring(0, directoryName.length() - 1));

            return "bucket";
        } else {
            String objectName = url.substring(9 + bucketname.length());

            List<ObjectVersion> versions = bucketService.getAllVersionsFromObject(objectName, bucketname);

            model.addAttribute("versions", versions);
            model.addAttribute("name", bucketname + objectName);

            return "file";
        }
    }

    @GetMapping("/download/{objectid}/{fileid}")
    public ResponseEntity<byte[]> download(@PathVariable int objectid, @PathVariable int fileid) {
        BucketFile file = bucketService.getFile(fileid);
        BucketObject object = bucketService.getObject(objectid);

        byte[] content = file.getContent();
        String name = object.getObjectname();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/octet-stream"));
        headers.setContentLength(content.length);
        headers.setContentDisposition(
                ContentDisposition.parse("attachment; filename=" + name)
        );

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
