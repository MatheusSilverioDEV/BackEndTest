package tgid.BackEnd.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tgid.BackEnd.api.domain.empresa.DadosCadastroEmpresa;
import tgid.BackEnd.api.domain.empresa.DadosEditaEmpresa;
import tgid.BackEnd.api.domain.empresa.Empresa;
import tgid.BackEnd.api.domain.empresa.EmpresaRepository;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;

    @GetMapping("/cadastro") // carrega a página de cadastro de empresa
    public String carregaPaginaCadastroEmpresa(Model model) {
        return "cadastro_empresa";
    }

    @PostMapping("/cadastrar") // cadastra empresa
    @Transactional
    public String cadastrarEmpresa(DadosCadastroEmpresa dados) {
        var empresa = new Empresa(dados);
        repository.save(empresa);
        System.out.println(empresa);
        return "redirect:/empresas/listar";
    }

    @GetMapping("/listar") // carrega lista de empresas cadastradas
    public String listarEmpresas(Model model) {
        List<Empresa> empresas = repository.findAll();
        model.addAttribute("empresas", empresas);
        return "empresa/empresas";
    }

    @GetMapping("/editar") // carrega página de edição de empresa
    public String carregaPaginaEdicaoEmpresa(@RequestParam Long id, Model model) {
        Empresa empresa = repository.findById(id).orElse(null);
        model.addAttribute("empresa", empresa);
        return "empresa/editar_empresa";
    }

    @PostMapping("/editar-empresa") // edita empresa
    public String editarEmpresa(DadosEditaEmpresa dados) {
        var empresa = repository.findById(dados.id()).orElse(null);
        if (empresa != null) {
            empresa.atualizaDadosEmpresa(dados);
            repository.save(empresa);
        }
        return "redirect:/empresas/listar";
    }

    @PostMapping("/excluir") // exclui empresa
    public String excluirEmpresa(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/empresas/listar";
    }
}