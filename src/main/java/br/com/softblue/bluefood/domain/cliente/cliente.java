package br.com.softblue.bluefood.domain.cliente;

import javax.persistence.Entity;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuppressWarnings("serial")
@Entity
public class cliente extends Usuario {
	
	private String cpf;
	
	private String cep;

}
