package oauth.repository;

import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class BookListRepo {
    private Map<String,Integer> BookList = Stream.of(new Object[][]{
            {"Data Structure & Algo", 100},
            {"SpringBoot 3", 150},
            {"Apache Kafka Fundamentals", 75}
    }).collect(Collectors.toMap(data -> (String) data[0], data-> (Integer) data[1]));

    public Integer getBook(String bookName){
        return BookList.get(bookName);
    }
}
