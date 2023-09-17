package tgid.BackEnd.api.domain.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import tgid.BackEnd.api.domain.cliente.Cliente;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
