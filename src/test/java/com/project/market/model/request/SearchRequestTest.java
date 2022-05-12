package com.project.market.model.request;

import com.project.market.dto.req.SearchDtoRequest;
import com.project.market.entity.information.InformationMarket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestTest {
    private SearchDtoRequest searchDtoRequest;
    private List<InformationMarket> informationMarket;
}
