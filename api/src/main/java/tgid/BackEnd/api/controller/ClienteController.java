package tgid.BackEnd.api.controller;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tgid.BackEnd.api.domain.cliente.*;

import java.util.List;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/cadastro") //carrega a pagina nesse controller
    public String carregaPaginadeCadastroCliente(Model model) {
        return "cadastro_cliente";
    }

    @PostMapping("/cadastrar") //cadastra cliente.
    @Transactional
    public String cadastrarCliente(DadosCadastroCliente dados) {
        var cliente = new Cliente(dados);
        repository.save(cliente);
        System.out.println(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/listar") // carrega lista com clientes cadastrados
    public String listarClientes(Model model) {
        List<Cliente> clientes = repository.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente/clientes";
    }

    @GetMapping("/editar") // exclui cliente
    public String carregaPaginaEdicaoCliente(@RequestParam Long id, Model model) {
        Cliente cliente = repository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "cliente/editar_cliente";
    }

    @PostMapping("/editar-cliente") // edita cliente
    public String editarCliente(DadosEditaCliente dados){
        var cliente = repository.findById(dados.id()).orElse(null);
        if (cliente != null){
            cliente.atualizaDadosCliente(dados);
            repository.save(cliente);
        }
        return "redirect:/clientes/listar";

    }


    @PostMapping("/excluir")
    public String excluirCliente(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/clientes/listar";
    }

}