package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PortServiceImpl implements PortService {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @Override
    public Integer getPort() {
        int port = webServerAppCtxt.getWebServer().getPort();
        return port;
    }
}