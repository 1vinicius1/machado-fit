import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Layouts
import { SimpleLayoutComponent } from './layout/simple-layout/simple-layout.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';

// Guards
import { AuthGuard } from './guards/auth.guard';

// Páginas
import { LoginComponent } from './pages/login/login.component';
import { PersonalAlunosComponent } from './pages/personal-alunos/personal-alunos.component';
import { AlunoTreinoComponent } from './pages/aluno-treino/aluno-treino.component';

// ATENÇÃO: CadastroAluno ainda não foi criado o código, deixei comentado para não dar erro
// import { CadastroAlunoComponent } from './pages/cadastro-aluno/cadastro-aluno.component';

const routes: Routes = [
  // Rota Pública (Login)
  {
    path: '',
    component: SimpleLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: '', redirectTo: 'login', pathMatch: 'full' }
    ]
  },

  // Rotas Protegidas (Logado)
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      // Área do Personal
      { 
        path: 'personal/alunos', 
        component: PersonalAlunosComponent 
      },
      // { 
      //   path: 'personal/novo-aluno', 
      //   component: CadastroAlunoComponent 
      // },
      
      // Área do Aluno
      { 
        path: 'aluno/meus-treinos', 
        component: AlunoTreinoComponent 
      }
    ]
  },

  // Rota Curinga (Redireciona erros 404 para login)
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }