import {ExerciseResult} from '../../exercise_result/model/exercise-result';

export interface Exercise {
  id: string;
  name: string;
  muscleGroup: string;
  equipment: string;
  duration: number;
  results: ExerciseResult[];
}
