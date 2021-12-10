package com.example.foodstorecomparison;

import com.example.foodstorecomparison.FoodStoreComparison.ProductService;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ScheduledTask {
//
    @Autowired
    private ProductService productService;
//5000 refreshib iga 5 sek tagant.
// fixeddelay jätab protsesside vahele 5 sek. fixedrate teeb iga 5 sek tagant
    @Scheduled(fixedDelay = 5000000)
    public void test() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        RestTemplate restTemplate = this.getRestTemplate();

        //prismaImport(restTemplate);
        selverImport(restTemplate);

    }
    private void selverImport(RestTemplate restTemplate) {

        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity("https://www.selver.ee/api/catalog/vue_storefront_catalog_et/product/_search?_source_exclude=*.msrp_display_actual_price_type,required_options&_source_include=documents,final_price,name,original_price_incl_tax,original_price,price,price_incl_tax,sale,special_price,special_price_incl_tax,status,*image,*small_image,manufacturer,product_main_ecategory,product_main_ean&from=0&request=%7B\"query\":%7B\"bool\":%7B\"filter\":%7B\"bool\":%7B\"must\":%5B%7B\"terms\":%7B\"visibility\":%5B2,3,4%5D%7D%7D,%7B\"terms\":%7B\"status\":%5B0,1%5D%7D%7D,%7B\"terms\":%7B\"category_ids\":%5B209,210,212,213,214,215,216,217,369%5D%7D%7D%5D%7D%7D%7D%7D,\"aggs\":%7B\"agg_terms_price\":%7B\"terms\":%7B\"field\":\"price\"%7D%7D,\"agg_terms_product_main_ecategory\":%7B\"terms\":%7B\"field\":\"product_main_ecategory\",\"size\":100%7D%7D,\"agg_terms_product_segment_options\":%7B\"terms\":%7B\"field\":\"product_segment_options\",\"size\":100%7D%7D%7D,\"sort\":%5B%7B\"category.position\":%7B\"order\":\"asc\",\"mode\":\"min\",\"nested_path\":\"category\",\"nested_filter\":%7B\"term\":%7B\"category.category_id\":209%7D%7D%7D%7D%5D%7D&size=4000&sort=position:asc", HashMap.class);
        Map body = responseEntity.getBody();
        Map hitsObject = (Map) body.get("hits");
        List<Map> items = (List) hitsObject.get("hits");
        for (Map element : items) {
            Map elementData = (Map) element.get("_source");
//            System.out.println(elementData.get("name"));
//            System.out.println(elementData.get("price"));
//            System.out.println(elementData.get("image"));
//            System.out.println(elementData.get("product_main_ean"));
            //System.out.println(elementData.get("product_main_ecategory"));
            //System.out.println();

            String name = (String) elementData.get("name");
            String ean = (String) elementData.get("product_main_ean");
            Object tmp = elementData.get("price");
            Double selverPrice = null;
            if(tmp instanceof Integer){
                Integer tmpPrice = (Integer) tmp;
                selverPrice = Double.valueOf(tmpPrice);
            } else {
                selverPrice = (Double) tmp;
            }
            List<Integer> selverCategory = (List) elementData.get("product_main_ecategory");
            //System.out.println(selverCategory.get(0));
            productService.addSelverInfo(name, ean, selverPrice, selverCategory.get(0));
        }

    }

    private void prismaImport(RestTemplate restTemplate) {
        for(int i = 16972; i <= 16977; i++) {
            String name = null;
            boolean morePages = true;
            for(int x = 1; morePages; x++) {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://www.prismamarket.ee/products/" + i + "/page/" + x, String.class);
                if(responseEntity == null) {
                    continue;
                }
                String body = responseEntity.getBody();
                //System.out.println(body);
                Document doc = Jsoup.parse(body);
                Elements elements = doc.select(".item");
//        System.out.println(elements);
                for (int j = 1; j < elements.size(); j++) {
                    Element element = elements.get(j);
                    Attributes attributes = element.attributes();
                    String ean = attributes.get("data-ean");
                    Elements nameElement = element.select(".name");
                    Elements priceElement = element.select(".unit-price");
                    Integer categoryElement = i;
                    //Element imgElement = element.select("img src");

                    if (j == 1 && name != null && name.equals(nameElement.html())) {
                        morePages = false;
                        break;
                    }
                    if(j == 1){
                        name = nameElement.html();
                    }
                    if(ean.isEmpty()) {
                        continue;
                    }

                    String[] parts = priceElement.html().split(" ");
                    String price = parts[0];
                    double prismaPrice = Double.parseDouble(parts[0].replace(",",".") );

                    System.out.println("EAN: " + ean);
                    System.out.println("Name: " + nameElement.html());
                    System.out.println("Price: " + prismaPrice);
                    System.out.println("Category: " + categoryElement);
                    System.out.println();


                    productService.createProduct(nameElement.html(), ean, prismaPrice, null, i,null);

                }
            }

        }
    }

    public RestTemplate getRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}