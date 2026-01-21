create schema if not exists machado_fit;

create type machado_fit.perfil_usuario_enum as enum ('PERSONAL', 'ALUNO');

create type machado_fit.dia_semana_enum as enum ('DOMINGO', 'SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO');

create table machado_fit.usuario(
	id serial primary key,
	nome varchar(100) not null,
	cpf varchar(14) not null unique, -- cpf deve ser único para login
	senha varchar(255) not null,
	data_nascimento date,
	perfil perfil_usuario_enum not null
); 

create table machado_fit.treino(
	id serial primary key,
	aluno_id integer not null,
	dia_semana dia_semana_enum not null,
	nome varchar(100) not null,
	constraint fk_treino_aluno foreign key (aluno_id) references usuario(id) on delete cascade,
	constraint uk_treino_dia unique (aluno_id, dia_semana)
);

create table machado_fit.exercicio(
	id serial primary key,
	treino_id integer not null,
	nome varchar(100) not null,
	repeticoes varchar(50) not null,
	carga varchar(50),
	observacoes text,
	constraint fk_exercicio_treino foreign key (treino_id) references treino(id) on delete cascade
);

create table machado_fit.historico_treino(
	id serial primary key,
	aluno_id integer not null,
	treino_id integer not null,
	data_conclusao date default current_date,
	constraint fk_historico_aluno foreign key (aluno_id) references usuario(id) on delete cascade,
	constraint fk_historico_treino foreign key (treino_id) references treino(id) on delete set null
)

ALTER TABLE machado_fit.usuario ALTER COLUMN perfil TYPE VARCHAR(20);

alter table machado_fit.usuario add status varchar(20) not null default 'Ativo';