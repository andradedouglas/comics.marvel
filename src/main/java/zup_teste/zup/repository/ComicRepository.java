package zup_teste.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zup_teste.zup.model.Comic;

public interface ComicRepository extends JpaRepository<Comic,Integer> {
}
