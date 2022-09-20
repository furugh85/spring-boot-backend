package com.anerkenung.springboot.config;


import com.anerkenung.springboot.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;



public class PaginationUtils {


    private PaginationUtils() {
    }


    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page, String baseUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String generateUri(String baseUrl, int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
    }
}



//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            List<String> description = Files.readAllLines(java.nio.file.Paths.get("exams.txt"), StandardCharsets.UTF_8);
//
//            for (String line : description) {
//                CourseDescriptionEntitiy courseDescriptionEntitiy1 = new CourseDescriptionEntitiy();
//                String[] tokens = line.split("_");
//
//                courseDescriptionEntitiy1.setExamnumber(tokens[0]);
//                courseDescriptionEntitiy1.setDescription(tokens[1]);
//                descriptionRepository.save(courseDescriptionEntitiy1);
//                System.out.println(descriptionRepository.findByExamnumber("9802"));
//
//                // System.out.println(courseDescriptionEntitiy1.toString());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
//        return corsFilter;
//    }

//    @Autowired
//    DescriptionRepository descriptionRepository;

