package com.example.demo.repositories;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeById(Long id);

//    @Query(value = "SELECT * FROM employee JOIN department ON employee.department_id ="
//            + "department.id JOIN post ON employee.post_id = post.id WHERE department_name ="
//            + ":departmentName AND post_name = :postName")
    List<Employee> findEmployeesByDepartment_DepartmentNameAndPost_PostName(String departmentName, String postName);
}
