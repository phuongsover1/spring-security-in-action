package com.learn_security.services;

import com.learn_security.models.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private Map<String, Employee> records =
            Map.of(
                    "emma", new Employee("Emma Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("ACCOUNTANT", "READER")),
                    "natalie"
                    , new Employee("Natalie Parker"
                            , List.of("Beautiful Paris"),
                            List.of("RESEARCHER"))
            );

    @PostAuthorize("returnObject.roles.contains('READER')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
