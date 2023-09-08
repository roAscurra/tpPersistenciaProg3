package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
