import {ExerciseListComponent} from './exercise/view/exercise-list/exercise-list.component';
import {ExerciseFormComponent} from './exercise/view/exercise-form/exercise-form.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: "exercises",
    component: ExerciseListComponent
  },
  {
    path: "exercise/new",
    component: ExerciseFormComponent
  },
  {
    path: "exercise/:uuid/edit",
    component: ExerciseFormComponent
  },
  { path: '', redirectTo: 'exercises', pathMatch: 'full' }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}

