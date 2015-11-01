package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.ContaController;
import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;


@Controller
public class ControllerUsuario {
	
	
	
	@RequestMapping("/loginForm")
	public String fomulario(){
		return "usuario/login";
	}
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession  session){
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.existeUsuario(usuario)){
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
		
	}
	
	@RequestMapping("/deslogar")
	public String deslogar(HttpSession  session){
		session.invalidate();
		return "redirect:loginForm";
		
	}
}
