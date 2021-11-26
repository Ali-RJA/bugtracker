package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
