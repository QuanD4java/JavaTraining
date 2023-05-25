package com.example.importandexportcsv.repository;

import com.example.importandexportcsv.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ModelRepository extends JpaRepository<Model,String> {

    @Query("select m from Model m")
    public Stream<Model> streamData();
}
