package br.com.caelum.contas;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;


@Controller
public class ContaController {
		
	private ContaDAO dao;
	
	@Autowired //instacia um Novo (new)
	public ContaController(ContaDAO dao){	
		this.dao = dao;
		
	}
	
	
	
	@RequestMapping("/form")
	public String formulario(){
		return "formulario";
	}
	
	
	@RequestMapping("/adicionarConta")		   //Com BeanValidator
	public String adiciona(@Valid Conta conta, BindingResult result){
		
		/*if(conta.getDescricao() == null || conta.getDescricao().equals("")){
			return "formulario";
		}
		*/
		if(result.hasErrors()){//Tem Erro? BeanValidator
			return "formulario";
		}
		
		dao.adiciona(conta);
		return "conta-adicionada";
	}
	
	
	@RequestMapping("/removerConta")
	public String remove(Conta conta){
		dao.remove(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/pagarConta")
	public void paga(Conta conta, HttpServletResponse response){
		dao.paga(conta.getId());
		
		response.setStatus(200);
	}
	
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		List<Conta> contas = dao.lista();
		
		ModelAndView modelAndView = new ModelAndView("conta/lista");
		modelAndView.addObject("contas", contas);//Para a JSP entender a variavel Contas e Atribuir seus respectivos varlores
		
		return modelAndView;
	}
}
