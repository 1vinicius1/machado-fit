
create user app_machado_fit with password 'app_machado_fit';
	
grant connect on database "postgres" to app_machado_fit;

grant usage on schema machado_fit to app_machado_fit;

grant select, insert, update, delete on all tables in schema machado_fit to app_machado_fit;

grant usage, select on all sequences in schema machado_fit to app_machado_fit;

alter default privileges in schema machado_fit grant select, insert, update, delete on tables to app_machado_fit;
