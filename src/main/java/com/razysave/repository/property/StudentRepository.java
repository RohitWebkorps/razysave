package com.razysave.repository.property;

import com.razysave.entity.property.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
}
