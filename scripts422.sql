CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY,
    brand_model TEXT,
    price NUMERIC
);
CREATE TABLE person (
    person_id SERIAL,
    name TEXT PRIMARY KEY,
    age INTEGER,
    driver_license BOOLEAN,
    car_id SERIAL REFERENCES cars (car_id)
);
select person.name, person.age, person.driver_license, person.car_id, cars.brand_model, cars.price
from person INNER JOIN cars ON person.car_id = cars.car_id;