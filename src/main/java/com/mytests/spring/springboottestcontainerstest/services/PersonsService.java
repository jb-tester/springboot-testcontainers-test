package com.mytests.spring.springboottestcontainerstest.services;

import com.mytests.spring.springboottestcontainerstest.data.Person;
import com.mytests.spring.springboottestcontainerstest.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonsService {



    public void initDB(){
        personRepository.
       personRepository.save(new Person(1,"vasya","pupkin",25,"default"));
       personRepository.save(new Person(2,"vanya","petrov",15,"default"));
       personRepository.save(new Person(3,"valya","sidorov",15,"default"));
       personRepository.save(new Person(4,"petya","ivanov",30,"default"));
       personRepository.save(new Person(5,"pasha","pavlov",33,"default"));

   }

}