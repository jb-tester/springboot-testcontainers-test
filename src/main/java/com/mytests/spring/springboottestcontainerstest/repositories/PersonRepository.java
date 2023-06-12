package com.mytests.spring.springboottestcontainerstest.repositories;


import com.mytests.spring.springboottestcontainerstest.data.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findBySurname(String surname);

    @Modifying
    @Query("update Person u set u.status = :status where u.age >= :age")
    void updateStatus(@Param("status") String s, @Param("age") int age);

    List<Person> getByStatus(String status);
}
