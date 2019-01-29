package ru.project.viviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.entity.Interest;
import ru.project.viviv.repository.InterestRepository;

import java.util.UUID;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;

    public void saveInterest(Interest interest){
        interestRepository.save(interest);
    }

    public Interest getInterestById(UUID id){
        return interestRepository.findById(id).orElse(null);
    }
}
