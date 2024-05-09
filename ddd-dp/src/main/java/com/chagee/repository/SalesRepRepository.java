package com.chagee.repository;

import com.chagee.domain.SalesRep;
import org.springframework.stereotype.Repository;

@Repository
public class SalesRepRepository {

    public SalesRep findRep(String areaCode){

        SalesRep salesRep = new SalesRep();
        salesRep.setRepId(123L);
        return salesRep;
    }
}
