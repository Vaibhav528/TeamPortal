package com.vaibhav.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.entites.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
