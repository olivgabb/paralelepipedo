package com.api.paralelepipedo.dtos;

import java.sql.Date;

public record RegisterProfessorDTO(String nome, String email, String senha, Date nascimento, String rg, 
		String telefone, String formacao, int num_vinculo, String tipo_vinculo, String cpf) {

}
