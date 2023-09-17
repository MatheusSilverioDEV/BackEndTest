package tgid.BackEnd.api.domain.Infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tgid.BackEnd.api.domain.empresa.Empresa;
import tgid.BackEnd.api.domain.empresa.EmpresaRepository;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TaxaSistemaRepository taxaSistemaRepository;

    public void realizarTransacao(Transacao transacao) {
        Empresa empresa = empresaRepository.findById(transacao.getEmpresaId()).orElse(null);

        if (empresa != null) {
            BigDecimal valorTransacao = transacao.getValor();

            BigDecimal taxasTotais = BigDecimal.ZERO;
            for (TaxaSistema taxa : empresa.getTaxas()) {
                BigDecimal taxaValor = taxa.getValor();

                BigDecimal taxaCalculada = valorTransacao.multiply(taxaValor);
                taxasTotais = taxasTotais.add(taxaCalculada);
            }

            BigDecimal valorLiquido = valorTransacao.subtract(taxasTotais);

            BigDecimal novoSaldo = empresa.getSaldo().add(valorLiquido);
            empresa.setSaldo(novoSaldo);

            empresaRepository.save(empresa);
            transacao.setTaxasAplicadas(taxasTotais);
        }
    }
}
