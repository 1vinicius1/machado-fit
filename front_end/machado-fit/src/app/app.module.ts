import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from "./app-routing.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

// --- Componentes Principais ---
import { AppComponent } from "./app.component";

// --- Layouts (Mantidos do seu projeto original) ---
// Certifique-se que estes arquivos existem nestas pastas
import { HeaderComponent } from "./components/header/header.component";
import { SidebarComponent } from "./components/sidebar/sidebar.component";
import { SimpleLayoutComponent } from "./layout/simple-layout/simple-layout.component";
import { MainLayoutComponent } from "./layout/main-layout/main-layout.component";

// --- Páginas (Apenas as que criamos o código) ---
import { LoginComponent } from "./pages/login/login.component";
import { PersonalAlunosComponent } from "./pages/personal-alunos/personal-alunos.component";
import { AlunoTreinoComponent } from "./pages/aluno-treino/aluno-treino.component";

// --- Services ---
import { AuthService } from "./services/auth.service";
import { TreinoService } from "./services/treino.service";
import { UsuarioService } from "./services/usuario.service";
import { HistoricoService } from "./services/historico.service";

// --- Guards ---
import { AuthGuard } from "./guards/auth.guard";
import { EditarAlunoComponent } from "./pages/editar-aluno/editar-aluno.component";
import { CadastrarAlunoComponent } from "./pages/cadastrar-aluno/cadastrar-aluno.component";
import { FooterComponent } from "./components/footer/footer.component";
import { DadosAlunoComponent } from './pages/dados-aluno/dados-aluno.component';
import { PersonalTreinosAlunosComponent } from './pages/personal-treinos-alunos/personal-treinos-alunos.component';
import { CadastrarExercicioComponent } from './pages/cadastrar-exercicio/cadastrar-exercicio.component';

@NgModule({
  declarations: [
    AppComponent,
    // Layouts e Componentes Estruturais
    HeaderComponent,
    SidebarComponent,
    SimpleLayoutComponent,
    MainLayoutComponent,
    // Páginas Funcionais
    LoginComponent,
    PersonalAlunosComponent,
    AlunoTreinoComponent,
    EditarAlunoComponent,
    CadastrarAlunoComponent,
    FooterComponent,
    DadosAlunoComponent,
    PersonalTreinosAlunosComponent,
    CadastrarExercicioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // Necessário para [(ngModel)]
    HttpClientModule, // Necessário para chamadas API
    BrowserAnimationsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [
    AuthService,
    TreinoService,
    UsuarioService,
    HistoricoService,
    AuthGuard,
  ],
  bootstrap: [AppComponent],
})

export class AppModule {}
