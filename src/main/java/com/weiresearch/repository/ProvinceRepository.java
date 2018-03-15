package com.weiresearch.repository;

import com.weiresearch.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

//    @Query("select new Province(p.id,p.name) from Province p where p.country=:country")
//    List<Province> getProvinceRepository(@Param("country") String country);

    List<Province> findByCountry(String country);

}

