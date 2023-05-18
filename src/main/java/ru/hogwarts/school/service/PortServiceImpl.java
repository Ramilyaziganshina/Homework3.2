package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PortServiceImpl implements PortService {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @Override
    public Integer getPort() {
        int port = webServerAppCtxt.getWebServer().getPort();
        return port;
    }

    @Override
    public Integer sum() {
        Integer sum = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
}