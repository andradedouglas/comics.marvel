package zup_teste.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zup_teste.zup.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByCpf(String cpf);
    User findByEmail(String email);
}
