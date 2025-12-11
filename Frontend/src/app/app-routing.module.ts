import {ExerciseListComponent} from './exercise/view/exercise-list/exercise-list.component';
import {ExerciseFormComponent} from './exercise/view/exercise-form/exercise-form.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExerciseResultFormComponent} from './exercise_result/view/exercise-result-form/exercise-result-form.component';
import {ExerciseResultListComponent} from './exercise_result/view/exercise-result-list/exercise-result-list.component';
import {ExerciseResultDetailsComponent} from './exercise_result/view/exercise-result-details/exercise-result-details.component'

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
  {
    path: "exercise/:uuid/results",
    component: ExerciseResultListComponent
  },
  {
    path: "exercise/:uuid/result/new",
    component: ExerciseResultFormComponent
  },
  {
    path: "exercise/:uuid/result/:resultId/edit",
    component: ExerciseResultFormComponent
  },
  {
    path: "exercise/:uuid/result/:resultId",
    component: ExerciseResultDetailsComponent
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

