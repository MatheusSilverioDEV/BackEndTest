package tgid.BackEnd.api.domain.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TaxaSistemaRepository extends JpaRepository<TaxaSistema, Long> {
}
