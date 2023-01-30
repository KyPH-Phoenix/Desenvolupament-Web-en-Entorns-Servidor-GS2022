package com.liceu.objects.controller;

import com.liceu.objects.exception.DistinctPasswordException;
import com.liceu.objects.exception.IncorrectPasswordOrUsernameException;
import com.liceu.objects.exception.UsernameAlreadyExistsException;
import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketObject;
import com.liceu.objects.model.User;
import com.liceu.objects.service.BucketService;
import com.liceu.objects.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    UserService userService;

    @Autowired
    BucketService bucketService;

    @GetMapping("/")
    public RedirectView redirectToLogin() {
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String loginGet() {

        return "login";
    }

    @PostMapping("/login")
    public RedirectView loginPost(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userService.getUserByUsername(username, password);
            session.setAttribute("user", user);
        } catch (IncorrectPasswordOrUsernameException e) {
            // Incorrect Password
            System.out.println("Error, usuario o contraseña incorrecto");
            return new RedirectView("/login");

        }

        return new RedirectView("/objects");
    }

    @GetMapping("/signup")
    public String registerGet() {

        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView registerPost(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rPassword = req.getParameter("rPassword");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        try {
            userService.registerUser(username, password, rPassword, firstname, lastname, email);
        } catch (DistinctPasswordException e) {
            // Password distinta
        } catch (UsernameAlreadyExistsException e) {
            // Username repetido
        }

        return new RedirectView("/login");
    }

    @GetMapping("/objects")
    public String objectsGet(HttpServletRequest req) {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        List<Bucket> buckets = bucketService.getBucketsFromUser(user);

        req.setAttribute("username", user.getUsername());
        req.setAttribute("buckets", buckets);

        return "object";
    }

    @PostMapping("/objects")
    public RedirectView objectsPost(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String username = ((User) session.getAttribute("user")).getUsername();
        String bucketname = req.getParameter("name");

        bucketService.createBucket(bucketname, username);

        return new RedirectView("/objects");
    }

    @GetMapping("/objects/{bucketname}")
    public String bucketGet(@PathVariable String bucketname, Model model) {
        List<BucketObject> objects = bucketService.getAllObjectsFromBucket(bucketname);

        model.addAttribute("objects", objects); 
        model.addAttribute("bucketname", bucketname);

        return "bucket";
    }


    /*
    @GetMapping("objects/{bucket}/**")
    public String getObject(HttpServletRequest req) {
        String url = (String) req.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE
        );
    }
    */

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
