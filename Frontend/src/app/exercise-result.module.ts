import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ExerciseResultFormComponent} from './exercise_result/view/exercise-result-form/exercise-result-form.component';
import {ExerciseResultListComponent} from './exercise_result/view/exercise-result-list/exercise-result-list.component';

@NgModule({
  declarations: [
    ExerciseResultFormComponent,
    ExerciseResultListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  exports: [
    ExerciseResultFormComponent,
    ExerciseResultListComponent,
  ]
})
export class ExerciseResultModule {}
