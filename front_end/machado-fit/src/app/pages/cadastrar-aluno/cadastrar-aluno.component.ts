import { Component, OnInit } from '@angular/core';
import { UsuarioDTO } from "../../models/usuario.model";
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cadastrar-aluno',
  templateUrl: './cadastrar-aluno.component.html'
})
export class CadastrarAlunoComponent implements OnInit {

  aluno: UsuarioDTO = {
    id: 0,
    nome: '',
    cpf: '',
    dataNascimento: '',
    perfil: 'ALUNO'
  };

  loading: boolean = false;
  salvando: boolean = false;
  alunoId: number = 0;

  constructor(
      private usuarioService: UsuarioService,
      private route: ActivatedRoute,
      private router: Router
    ) {}

  ngOnInit() {
  }

  salvar() {

    this.salvando = true;
    const cmd = {
      id: this.aluno.id,
      nome: this.aluno.nome,
      cpf: this.aluno.cpf,
      dataNascimento: this.aluno.dataNascimento,
      perfil: this.aluno.perfil
    };

    this.usuarioService.cadastrar(cmd).subscribe(
      res => {
        alert('Aluno cadastrado com sucesso!');
        this.salvando = false;
        this.router.navigate(['/personal/alunos']);
      },
      err => {
        console.error('Erro ao cadastrar aluno:', err);
        alert('Erro ao cadastrar aluno.');
        this.salvando = false;
      }
    );
  }

  cancelar() {
    this.router.navigate(['/personal/alunos']);
  }

}
