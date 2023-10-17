package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUserByCpf(String cpf);

    Optional<Usuario> findUserById(Long Id);
}
