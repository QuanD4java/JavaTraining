package com.example.importandexportcsv.repository;

import com.example.importandexportcsv.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,String> {
}
