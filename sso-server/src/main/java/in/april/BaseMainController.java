package in.april;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseMainController {


    /**
     * 自定义登录页面
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginPage(Model model){
        //TODO: loginProcessUrl默认与loginPage相同，作用？
        model.addAttribute("loginProcessUrl","/sso/login");
        return "base-login";
    }
}