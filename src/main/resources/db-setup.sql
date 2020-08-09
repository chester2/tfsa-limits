DROP TABLE IF EXISTS limits;

SET MODE MySQL;

CREATE TABLE limits (
    year INT PRIMARY KEY,
    amount DECIMAL(13, 4)
);

INSERT INTO limits (year, amount) VALUES
    (2009, 5000),
    (2010, 5000),
    (2011, 5000),
    (2012, 5000),
    (2013, 5500),
    (2014, 5500),
    (2015, 10000),
    (2016, 5500),
    (2017, 5500),
    (2018, 5500),
    (2019, 6000),
    (2020, 6000);
