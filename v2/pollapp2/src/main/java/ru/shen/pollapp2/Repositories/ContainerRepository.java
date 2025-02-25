package ru.shen.pollapp2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.shen.pollapp2.Models.Container;

public interface ContainerRepository extends JpaRepository<Container, Long>{

}
