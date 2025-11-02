package com.movie.Movie.service;


import com.movie.Movie.domain.Revenue;

import static com.movie.Movie.util.CommonUtil.delay;

public class RevenueService {

    public Revenue getRevenue(Long movieId){
        delay(1000);
        return Revenue.builder() .movieInfoId(movieId)
                .budget(1000000) .boxOffice(5000000) .build();
    }
}
