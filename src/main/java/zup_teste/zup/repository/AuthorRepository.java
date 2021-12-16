package zup_teste.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zup_teste.zup.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    
}
