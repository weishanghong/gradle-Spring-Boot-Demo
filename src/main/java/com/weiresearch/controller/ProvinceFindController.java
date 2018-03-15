package com.weiresearch.controller;

import com.weiresearch.entity.Province;
import com.weiresearch.pojoentity.Pagination;
import com.weiresearch.pojoentity.ProvinceFindControllerResponse;
import com.weiresearch.repository.ProvinceRepository;
import com.weiresearch.repository.StorageImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/province")
public class ProvinceFindController {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private StorageImpl storage;
    @Autowired
    private  ProvinceRepository provinceRepository;


    @GetMapping("/getall")
    public List<Province> findProvinceList(@RequestParam(required = true) String country) throws Exception {
        return this.storage.getProvinceRepository().findByCountry(country);
    }

    //分页查询
    @GetMapping("/getlist")
    public ProvinceFindControllerResponse get(@RequestParam(defaultValue = "1", required = true) Integer pageNumber,
                                  @RequestParam(defaultValue = "10", required = true) Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
        Page<Province> ret = provinceRepository.findAll(pageable);
        Pagination pagination = new Pagination(pageNumber, pageSize, ret.getTotalElements(), ret.getTotalPages());
        return new ProvinceFindControllerResponse(pagination, ret.getContent());
    }
}
