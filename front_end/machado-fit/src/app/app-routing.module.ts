import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// Layouts
import { SimpleLayoutComponent } from "./layout/simple-layout/simple-layout.component";
import { MainLayoutComponent } from "./layout/main-layout/main-layout.component";

// Guards
import { AuthGuard, PerfilGuard } from "./guards/auth.guard";

// Páginas
import { LoginComponent } from "./pages/login/login.component";
import { PersonalAlunosComponent } from "./pages/personal-alunos/personal-alunos.component";
import { AlunoTreinoComponent } from "./pages/aluno-treino/aluno-treino.component";
import { EditarAlunoComponent } from "./pages/editar-aluno/editar-aluno.component";
import { CadastrarAlunoComponent } from "./pages/cadastrar-aluno/cadastrar-aluno.component";
import { DadosAlunoComponent } from "./pages/dados-aluno/dados-aluno.component";
import { PersonalTreinosAlunosComponent } from "./pages/personal-treinos-alunos/personal-treinos-alunos.component";

// ATENÇÃO: CadastroAluno ainda não foi criado o código, deixei comentado para não dar erro
// import { CadastroAlunoComponent } from './pages/cadastro-aluno/cadastro-aluno.component';

const routes: Routes = [
  // Rota Pública (Login)
  {
    path: "",
    component: SimpleLayoutComponent,
    children: [
      { path: "login", component: LoginComponent },
      { path: "", redirectTo: "login", pathMatch: "full" },
    ],
  },

  // Rotas Protegidas (Logado)
  {
    path: "",
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      // Área do Personal
      {
        path: "personal/alunos",
        component: PersonalAlunosComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["PERSONAL"] },
      },
      // {
      //   path: 'personal/novo-aluno',
      //   component: CadastroAlunoComponent
      // },

      // Área do Aluno
      {
        path: "aluno/meus-treinos",
        component: AlunoTreinoComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["ALUNO"]},
      },

      // Área do Personal - Treinos dos Alunos
      {
        path: "personal/treinos/:id",
        component: PersonalTreinosAlunosComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["PERSONAL"] },
      },

      // Editar Aluno
      {
        path: "personal/editar-aluno/:id",
        component: EditarAlunoComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["PERSONAL"] },
      },

      // Cadastrar Aluno
      {
        path: "personal/cadastrar-aluno",
        component: CadastrarAlunoComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["PERSONAL"] },
      },

      // Visualizar perfil do Usuário
      {
        path: "dados-usuario",
        component: DadosAlunoComponent,
        canActivate: [PerfilGuard],
        data: {
           perfis: ["ALUNO", "PERSONAL"]},
      }
    ],
  },

  // Rota Curinga (Redireciona erros 404 para login)
  { path: "**", redirectTo: "login" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
