package answer.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String goHome(){
//        return "ajax";
//    }

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String goHello(){
        return "hello";
    }
}
