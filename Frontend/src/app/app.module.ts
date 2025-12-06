import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { provideHttpClient } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ExerciseListComponent} from './exercise/view/exercise-list/exercise-list.component';
import {App} from './app';
import {ExerciseFormComponent} from './exercise/view/exercise-form/exercise-form.component';
import {MainComponent} from './core/component/main/main.component';
import {HeaderComponent} from './core/component/header/header.component';
import {FooterComponent} from './core/component/footer/footer.component';
import {NavComponent} from './core/component/nav/nav.component';

@NgModule({
  declarations: [
    App,
    ExerciseListComponent,
    ExerciseFormComponent,
    MainComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient()
  ],
  bootstrap: [
    App
  ]
})
export class AppModule {}
