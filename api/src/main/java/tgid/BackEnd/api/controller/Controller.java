package tgid.BackEnd.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/cadastro")
    public String carregaPaginaCadastro(){
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(String tipo_usuario) {
        if ("cliente".equals(tipo_usuario)) {
            //encaminhe para a página de cadastro de cliente.
            return "cliente/cadastro_cliente";
        } else if ("empresa".equals(tipo_usuario)) {
            //  escolheu Empresa, encaminhe para a página de cadastro de empresa.
            return "empresa/cadastro_empresa";
        } else {
            // return para lidar com opções desconhecidas ou erros.
            return "pagina_de_erro";
        }
    }
}


