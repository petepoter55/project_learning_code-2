package com.project.market.entity.repository;

import com.project.market.entity.information.InformationMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InformationMarketRepository extends JpaRepository<InformationMarket, Integer> {
    @Query("SELECT m FROM InformationMarket m WHERE m.firstName =:firstname and m.lastName =:lastname")
    InformationMarket findByFirstnameAndLastname(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname);
}
