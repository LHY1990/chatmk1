package webapp.chatmk1.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public ModelAndView getHome(ModelAndView mav) {
        mav.setViewName("templates/chat.html");

        return mav;
    }
}
