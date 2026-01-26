CREATE TABLE exercise_results (
    id UUID PRIMARY KEY,
    exercise_id UUID NOT NULL,
    which_set INT NOT NULL,
    reps INT NOT NULL,
    weight INT NOT NULL,
    personal_best BOOLEAN NOT NULL DEFAULT false,

    CONSTRAINT ck_exercise_results_set_positive CHECK (which_set > 0),
    CONSTRAINT ck_exercise_results_reps_positive CHECK (reps > 0),
    CONSTRAINT ck_exercise_results_weight_non_negative CHECK (weight >= 0)
);

CREATE INDEX ix_exercise_results_exercise_id
    ON exercise_results (exercise_id);

CREATE TABLE exercise_records (
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);