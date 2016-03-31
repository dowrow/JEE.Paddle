package web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    private static final List<String> THEMES = Arrays.asList("thymeleaf");

    @Autowired
    private ServletContext servletContext;

    private String theme = THEMES.get(0);

    public Presenter() {
    }

    // Se ejecuta siempre y antes. Añade un atributo al Model
    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss").format(new Date());
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("themes", THEMES);
        //La vista resultante no lleva extensión (.jsp) configurado en WebConfig.java
        return theme + "/home";
    }

}