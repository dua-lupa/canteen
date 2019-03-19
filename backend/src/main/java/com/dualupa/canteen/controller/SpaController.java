package com.dualupa.canteen.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author avbelyaev
 */
@Controller
public class SpaController {

    // TODO add routes from SPA here
    @GetMapping(value = {
            "/dishes/**"
    }, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView returnSpaBundle() {
        return new ModelAndView("index");
    }
}
