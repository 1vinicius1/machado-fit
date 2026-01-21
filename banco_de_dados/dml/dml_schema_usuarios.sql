insert into machado_fit.usuario (
	nome,
	cpf,
	senha,
	data_nascimento,
	perfil
) values ('Matheus Araujo', '06318913580', 'lenda123456', '2001-06-19', 'PERSONAL'),
		 ('Vinicius Cunha', '08276970581', 'lenda123456', '2003-02-19', 'ALUNO'),
		 ('Makalister Renton', '02406980567', 'lenda123456', '2002-08-29', 'ALUNO'),
		 ('Philipe Gabriel', '07489008569', 'lenda123456', '2002-06-10', 'ALUNO'),
		 ('Julia Moura', '07428508258', 'lenda123456', '2000-01-01', 'ALUNO'),
		 ('Derek Luccas', '02101107967', 'lenda123456', '2012-02-05', 'ALUNO'),
		 ('Iasmim Santos', '01201301485', 'lenda123456', '2002-02-10', 'ALUNO'),
		 ('Davy Jones', '07489012085', 'lenda123456', '1998-06-10', 'ALUNO'),
		 ('William Bonner', '08276978521', 'lenda123456', '1979-01-29', 'ALUNO'),
	   	 ('Fatima Bernardes', '08521010547', 'lenda123456', '1997-06-10', 'ALUNO');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(2, 'SEGUNDA', 'Treino A'),
	(2, 'TERCA', 'Treino B'),
	(2, 'QUARTA', 'Treino C'),
	(2, 'QUINTA', 'Treino D'),
	(2, 'SEXTA', 'Treino E');

insert into machado_fit.exercicio (treino_id, nome, repeticoes, carga, observacoes)
	values 
	(3, 'Leg Press', '3x12', '40kg', 'Execução completa'),
	(3, 'Cadeira Extensora', '3x12', '27kg', 'Execução completa'),
	(3, 'Mesa Flexora', '3x12', '30kg', 'Execução completa'),
	(3, 'Panturrilha', '3x12', '15kg', 'Execução completa'),
	(3, 'Adutora', '3x12', '30kg', 'Execução completa'),
	(3, 'Escada', '20min', '0kg', 'Execução completa');

insert into machado_fit.exercicio (treino_id, nome, repeticoes, carga, observacoes)
	values 
	(4, 'Desenvolvimento com Halteres', '3x12', '12kg', 'Execução controlada'),
	(4, 'Elevação Lateral', '3x12', '6kg', 'Não balançar o corpo'),
	(4, 'Elevação Frontal', '3x12', '6kg', 'Subir até a linha do ombro'),
	(4, 'Remada Alta', '3x12', '20kg', 'Pegada média'),
	(4, 'Rosca de Punho', '3x15', '10kg', 'Movimento completo'),
	(4, 'Rosca Inversa', '3x12', '15kg', 'Controle na descida');

insert into machado_fit.exercicio (treino_id, nome, repeticoes, carga, observacoes)
values 
	(5, 'Abdominal Reto', '3x15', '0kg', 'Contração máxima'),
	(5, 'Abdominal Infra', '3x15', '0kg', 'Movimento controlado'),
	(5, 'Abdominal Oblíquo', '3x12', '0kg', 'Alternar os lados'),
	(5, 'Prancha', '3x30s', '0kg', 'Manter postura'),
	(5, 'Elevação de Pernas', '3x12', '0kg', 'Não arquear a lombar'),
	(5, 'Esteira', '20min', '0kg', 'Ritmo moderado');


insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(3, 'SEGUNDA', 'Treino A'),
	(3, 'TERCA', 'Treino B'),
	(3, 'QUARTA', 'Treino C');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(4, 'SEGUNDA', 'Treino A'),
	(4, 'TERCA', 'Treino B'),
	(4, 'QUARTA', 'Treino C'),
	(4, 'QUINTA', 'Treino D');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(5, 'SEGUNDA', 'Treino A'),
	(5, 'TERCA', 'Treino B'),
	(5, 'QUARTA', 'Treino C');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(6, 'SEGUNDA', 'Treino A'),
	(6, 'TERCA', 'Treino B'),
	(6, 'QUARTA', 'Treino C'),
	(6, 'QUINTA', 'Treino D'),
	(6, 'SEXTA', 'Treino E');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(7, 'SEGUNDA', 'Treino A'),
	(7, 'TERCA', 'Treino B'),
	(7, 'QUARTA', 'Treino C'),
	(7, 'QUINTA', 'Treino D');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(8, 'SEGUNDA', 'Treino A'),
	(8, 'TERCA', 'Treino B'),
	(8, 'QUARTA', 'Treino C');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(9, 'SEGUNDA', 'Treino A'),
	(9, 'TERCA', 'Treino B'),
	(9, 'QUARTA', 'Treino C'),
	(9, 'QUINTA', 'Treino D');

insert into machado_fit.treino (aluno_id, dia_semana, nome)
	values 
	(10, 'SEGUNDA', 'Treino A'),
	(10, 'TERCA', 'Treino B'),
	(10, 'QUARTA', 'Treino C'),
	(10, 'QUINTA', 'Treino D'),
	(10, 'SEXTA', 'Treino E');

insert into machado_fit.historico_treino (aluno_id, treino_id)
	values
	(2, 2);

	







