package com.project.market.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.market.constant.Constant;
import com.project.market.entity.information.InformationMarket;
import com.project.market.entity.repository.InformationMarketRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.model.request.SearchRequestTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MarketInformationSearchTest {
    private final static Logger logger = Logger.getLogger(MarketInformationSearchTest.class);

    @InjectMocks
    private MarketInformationImpl marketInformation;
    @Mock
    private EntityManager entityManager;
    @Mock
    private InformationMarketRepository informationMarketRepository;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<InformationMarket> criteriaQuery;
    @Mock
    private Root<InformationMarket> root;
    @Mock
    private TypedQuery typedQuery;


    @Test
    public void searchByInputCase() throws Exception {
        File[] files = readTestCase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(simpleDateFormat);
        try {
            for(File file : files){
                // ARRANGE
                SearchRequestTest searchRequestTest = mapper.readValue(file, SearchRequestTest.class);
                setup(searchRequestTest);
                // ACT
                List<InformationMarket> actual = marketInformation.searchByInput(searchRequestTest.getSearchDtoRequest());
                // ASSERT
                List<InformationMarket> expected = mapper.readValue(FileUtils.readFileToString(new File(FilenameUtils.concat("./resources/information/case_search/result", file.getName())), StandardCharsets.UTF_8), new TypeReference<List<InformationMarket>>(){});
                for(int i = 0; i < actual.size(); i++){
                    assertEquals(expected.get(i).getFirstName(),actual.get(i).getFirstName());
                    assertEquals(expected.get(i).getLastName(),actual.get(i).getLastName());
                    assertEquals(expected.get(i).getEmail(),actual.get(i).getEmail());
                    assertEquals(expected.get(i).getDistrictName(),actual.get(i).getDistrictName());
                    assertEquals(expected.get(i).getReferenceNo(),actual.get(i).getReferenceNo());
                    assertEquals(expected.get(i).getPostcode(),actual.get(i).getPostcode());
                    assertEquals(expected.get(i).getRoad(),actual.get(i).getRoad());
                    assertEquals(expected.get(i).getProvinceName(),actual.get(i).getProvinceName());
                    assertEquals(expected.get(i).getTelephone(),actual.get(i).getTelephone());
                    assertEquals(expected.get(i).getSubProvinceName(),actual.get(i).getSubProvinceName());
                    assertEquals(expected.get(i).getType(),actual.get(i).getType());
                    assertEquals(expected.get(i).getAddress1(),actual.get(i).getAddress1());
                    assertEquals(expected.get(i).getAddress2(),actual.get(i).getAddress2());
                }
            }
        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
    }

    private File[] readTestCase() throws Exception {
        File folder = new File("./resources/information/case_search/testcase");
        return folder.listFiles();
    }

    private void setup(SearchRequestTest searchRequestTest) {
        List<InformationMarket> informationMarketList = new ArrayList<>();
        if(searchRequestTest.getInformationMarket() != null){
            for(InformationMarket data : searchRequestTest.getInformationMarket()){
                informationMarketList.add(data);
            }
        }

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(InformationMarket.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(InformationMarket.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(informationMarketList);
    }

}