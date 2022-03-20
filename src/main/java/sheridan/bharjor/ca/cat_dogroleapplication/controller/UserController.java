package sheridan.bharjor.ca.cat_dogroleapplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value={"/Home"})
    public String index(){
        return "Home";
    }

}
