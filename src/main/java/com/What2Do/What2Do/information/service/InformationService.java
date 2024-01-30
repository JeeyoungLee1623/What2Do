package com.What2Do.What2Do.information.service;

import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.information.etc.InformationDto;
import com.What2Do.What2Do.information.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository;

    public void create (InformationDto informationDto){
        Information information1 = Information.builder()
                .title(informationDto.getTitle())
                .content(informationDto.getContent())
                .imageUrl(informationDto.getImageUrl())
                .category(informationDto.getCategory2())
                .build();
        informationRepository.save(information1);
    }

    public List<Information> findAll() throws SQLException {
        List<Information> information2 = informationRepository.findAll();
        return information2;
    }

    public Information findById(Long myId) throws Exception {
        Information information3 = informationRepository.findById(myId).orElseThrow(Exception::new);
        return information3;
    }

    public void update (InformationDto informationDto) throws Exception {
        Information information4 = informationRepository.findById(Long.parseLong(informationDto.getId())).orElseThrow(Exception::new);
        information4.setCategory(informationDto.getCategory2());
        information4.setTitle(informationDto.getTitle());
        information4.setContent(informationDto.getContent());
        information4.setImageUrl(informationDto.getImageUrl());
        informationRepository.save(information4);
    }

    public void delete (Long id) throws Exception {
        informationRepository.delete(this.findById(id));
    }


















}
