package com.jreact.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CountryService {

    private final com.jreact.dynamodb.CountryRepository repository;

    public List<com.jreact.dynamodb.CountryEntity> getAll() {
        return repository.getAll();
    }
}
