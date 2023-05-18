-- liquibase formatted sql

-- changeset rziganshina:1
CREATE INDEX student_name_index ON student (name);

-- changeset rziganshina:2
CREATE INDEX faculty_nc_idx ON faculty (name, colour);