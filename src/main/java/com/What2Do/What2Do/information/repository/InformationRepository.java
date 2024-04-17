package com.What2Do.What2Do.information.repository;

import com.What2Do.What2Do.information.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {


}