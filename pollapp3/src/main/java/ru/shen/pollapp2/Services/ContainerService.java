package ru.shen.pollapp2.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.shen.pollapp2.Models.Container;
import ru.shen.pollapp2.Repositories.ContainerRepository;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;
    public ContainerService(ContainerRepository containerRepository){
        this.containerRepository = containerRepository;
    }

    public Container saveContainer(Container container){
        return containerRepository.save(container);
    }

    public List<Container> findAll(){
        return containerRepository.findAll();
    }

    public Container findById(Long id){
        return containerRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
