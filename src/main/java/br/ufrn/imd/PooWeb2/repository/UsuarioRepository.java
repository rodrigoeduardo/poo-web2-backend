package br.ufrn.imd.PooWeb2.repository;

import br.ufrn.imd.PooWeb2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
