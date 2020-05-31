CREATE TABLE IF NOT EXISTS formula(
formula_id VARCHAR(5) PRIMARY KEY,
formula_name VARCHAR(50),
value_year INT DEFAULT 0,
value_month INT DEFAULT 0,
value_day INT DEFAULT 0,
designer_day INT DEFAULT 0
)
;