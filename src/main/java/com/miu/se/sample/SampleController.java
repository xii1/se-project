package com.miu.se.sample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XIII
 */
@RestController
@RequestMapping("sample")
public class SampleController {

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello, this is sample controller.";
    }
}
