package com.manpower.repository;

import com.manpower.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByCompany_Id(Integer companyId);
}
