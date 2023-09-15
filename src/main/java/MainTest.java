import org.example.config.Property;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MainTest {
   static Property<String> SPAPI_RETURN_URL = Property.getInstance("spapiReturnUrl", "https://startpack.ru/");


    public static void main(String[] args) {

//        Set<String> set = Set.of("Slava", "Misha", "Otec");
//        Iterator<String> sourceIterator = set.iterator();
//        Stream<String> targetStream = StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(sourceIterator, Spliterator.ORDERED),
//                false);
//
//        Iterator<String> iterator = set.iterator();
//
//        String key = targetStream.findFirst().orElse(null);
//        String setKey = set.stream().findFirst().orElse(null);
//        System.out.println(key);
//        System.out.println(setKey);
//        System.out.println(iterator.next());
//        HttpStatus httpStatus = (HttpStatus) new HttpServerErrorException(HttpStatusCode.valueOf(400)).getStatusCode();
//        System.out.println(new HttpServerErrorException(HttpStatusCode.valueOf(400)).getStatusCode());
//        System.out.println(httpStatus);
    }




}
