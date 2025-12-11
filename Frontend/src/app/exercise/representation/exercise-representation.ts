import {ExerciseResultRepresentation} from '../../exercise_result/representation/exercise-result-representation';

export interface ExerciseRepresentation {
  id: string;
  name: string;
  muscleGroup: string;
  equipment: string;
  duration: number;
  results: ExerciseResultRepresentation[];
}
