package com.jreact.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository repository;

    public List<MusicEntity> getAll() {
        return repository.getAll();
    }
}
