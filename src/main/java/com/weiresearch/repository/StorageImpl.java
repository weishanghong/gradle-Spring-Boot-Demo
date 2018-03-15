package com.weiresearch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageImpl {

    @Autowired
    private ProvinceRepository provinceRepository;

    public ProvinceRepository getProvinceRepository() {
        return provinceRepository;
    }

}
