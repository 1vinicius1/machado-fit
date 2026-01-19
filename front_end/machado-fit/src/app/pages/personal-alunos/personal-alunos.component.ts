import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { UsuarioDTO } from '../../models/usuario.model';

@Component({
  selector: 'app-personal-alunos',
  templateUrl: './personal-alunos.component.html'
})
export class PersonalAlunosComponent implements OnInit {
  alunos: UsuarioDTO[] = [];
  loading: boolean = false;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
    this.carregarAlunos();
  }

  carregarAlunos() {
    this.loading = true;
    this.usuarioService.listarAlunos().subscribe(
      data => {
        this.alunos = data;
        this.loading = false;
      },
      err => {
        alert('Erro ao carregar alunos.');
        this.loading = false;
      }
    );
  }

  excluir(id: number) {
    if(confirm('Tem certeza que deseja remover este aluno e todos os seus dados?')) {
      this.usuarioService.excluir(id).subscribe(
        res => {
          alert('Aluno removido com sucesso!');
          this.carregarAlunos(); // Atualiza a lista
        },
        err => alert('Não foi possível excluir o aluno.')
      );
    }
  }



}