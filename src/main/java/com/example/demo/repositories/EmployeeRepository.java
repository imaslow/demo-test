package com.example.demo.repositories;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeById(Long id);

    @Query(value = "SELECT e FROM Employee e WHERE e.department.departmentName = :departmentName AND e.post.postName = :postName")
    List<Employee> findByDepartmentAndPost(@Param("departmentName")String departmentName, @Param("postName") String postName);
}
