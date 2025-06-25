INSERT INTO products (name, price_in_cents)
VALUES
    ('Tomato', 150),
    ('Onion', 100),
    ('Garlic', 50),
    ('Pasta', 200),
    ('Olive Oil', 500),
    ('Parmesan Cheese', 800),
    ('Basil', 120),
    ('Ground Beef', 600),
    ('Bell Pepper', 180),
    ('Mushrooms', 250),
    ('Chicken Breast', 700),
    ('Rice', 300),
    ('Black Beans', 180),
    ('Avocado', 220),
    ('Lime', 80);

INSERT INTO recipes (name, price_in_cents, description)
VALUES
    ('Pasta Marinara', 1200, 'Classic Italian pasta with tomato sauce and basil'),
    ('Beef Stir Fry', 1500, 'Quick and delicious beef stir fry with vegetables'),
    ('Chicken Rice Bowl', 1300, 'Healthy chicken and rice bowl with vegetables'),
    ('Vegetarian Pasta', 1000, 'Pasta with mushrooms, bell peppers and olive oil');

-- Insert recipe items (ingredients for each recipe)
-- Pasta Marinara recipe (recipe_id: 1)
INSERT INTO recipe_items (recipe_id, product_id, quantity)
VALUES
   (1, 1, 3),  -- 3 Tomatoes
   (1, 2, 1),  -- 1 Onion
   (1, 3, 2),  -- 2 Garlic
   (1, 4, 1),  -- 1 Pasta
   (1, 5, 1),  -- 1 Olive Oil
   (1, 7, 1);  -- 1 Basil

-- Beef Stir Fry recipe (recipe_id: 2)
INSERT INTO recipe_items (recipe_id, product_id, quantity)
VALUES
   (2, 8, 1),  -- 1 Ground Beef
   (2, 9, 2),  -- 2 Bell Peppers
   (2, 2, 1),  -- 1 Onion
   (2, 3, 2),  -- 2 Garlic
   (2, 5, 1);  -- 1 Olive Oil

-- Chicken Rice Bowl recipe (recipe_id: 3)
INSERT INTO recipe_items (recipe_id, product_id, quantity)
VALUES
   (3, 11, 1), -- 1 Chicken Breast
   (3, 12, 1), -- 1 Rice
   (3, 13, 1), -- 1 Black Beans
   (3, 14, 1), -- 1 Avocado
   (3, 15, 1); -- 1 Lime

-- Vegetarian Pasta recipe (recipe_id: 4)
INSERT INTO recipe_items (recipe_id, product_id, quantity)
VALUES
   (4, 4, 1),  -- 1 Pasta
   (4, 10, 1), -- 1 Mushrooms
   (4, 9, 1),  -- 1 Bell Pepper
   (4, 5, 1),  -- 1 Olive Oil
   (4, 6, 1);  -- 1 Parmesan Cheese
