package com.drewdomi.redcross.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.repositories.RescuerRespository;

import jakarta.transaction.Transactional;

@Service
public class RescuerService {

    public final RescuerRespository rescuerRespository;

    public RescuerService(RescuerRespository rescuerRespository) {
        this.rescuerRespository = rescuerRespository;
    }

    @Transactional
    public List<Rescuer> findAll() {
        return this.rescuerRespository.findAll();
    }
}
