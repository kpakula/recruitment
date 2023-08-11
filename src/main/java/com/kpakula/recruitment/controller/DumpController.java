package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.model.UserDump;
import com.kpakula.recruitment.service.DumpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dumps")
public class DumpController {
    private final DumpService service;

    public DumpController(DumpService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public UserDump createUserDumpFile() {
        return this.service.createUserDumpFile();
    }
}
