package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.application.exception.ValidationException;
import br.com.softblue.bluefood.application.service.ClienteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@GetMapping(path = "/home")
	public String home() {
		return "cliente-home";
	}

	@GetMapping("/edit")
	public String edit(Model model) {
		Integer clienteId = SecurityUtils.loggedCliente().getId();
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
		model.addAttribute("cliente", cliente);
		ControllerHelper.setEditMode(model, true);

		return "cliente-cadastro";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("cliente") @Valid Cliente cliente,
					   Errors errors,
					   Model model) {

		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente gravado com sucesso!");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}

		ControllerHelper.setEditMode(model, true);

		return "cliente-cadastro";
	}

}
