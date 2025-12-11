import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ExerciseResultFormComponent} from './exercise_result/view/exercise-result-form/exercise-result-form.component';
import {ExerciseResultListComponent} from './exercise_result/view/exercise-result-list/exercise-result-list.component';
import {ExerciseResultDetailsComponent} from './exercise_result/view/exercise-result-details/exercise-result-details.component';

@NgModule({
  declarations: [
    ExerciseResultFormComponent,
    ExerciseResultListComponent,
    ExerciseResultDetailsComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  exports: [
    ExerciseResultFormComponent,
    ExerciseResultListComponent,
    ExerciseResultDetailsComponent,
  ]
})
export class ExerciseResultModule {}
