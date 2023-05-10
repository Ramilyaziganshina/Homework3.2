CREATE TABLE person (
    name TEXT,
    age INTEGER,
    driver_license BOOLEAN,
    car_brand_model TEXT
);
CREATE TABLE car (
    brand_model TEXT,
    price NUMERIC
);
select person.name, person.age, person.driver_license, person.car_brand_model, car.price
from person INNER JOIN car ON person.car_brand_model = car.brand_model;