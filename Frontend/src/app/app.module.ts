import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { provideHttpClient } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ExerciseListComponent} from './exercise/view/exercise-list/exercise-list.component';
import {App} from './app';
import {ExerciseFormComponent} from './exercise/view/exercise-form/exercise-form.component';

@NgModule({
  declarations: [
    App,
    ExerciseListComponent,
    ExerciseFormComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
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
