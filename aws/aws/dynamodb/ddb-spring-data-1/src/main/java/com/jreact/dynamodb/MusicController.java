package com.jreact.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;

    @GetMapping
    public List<MusicEntity> getAll() {
        return service.getAll();
    }
}