package com.lanou.cn;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by landfash on 2017/7/13.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
public class Test {

    public static void login() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        bodyMap.add("prdDtlNo",10044);
        bodyMap.add("productOrderNumber","1001");
//        bodyMap.add("salePriceEnd",5000);
//        bodyMap.add("ip","192.168.2.2 ");
        Map<String,Object> result = restTemplate.postForObject("http://localhost:8080/rest/produceOrder.do",bodyMap, Map.class);
        System.out.println(result);
    }



    public static void main(String[] args){
        login();
    }
}
