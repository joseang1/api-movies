INSERT INTO genres (id, name) VALUES (default, 'Accion');
INSERT INTO genres (id, name) VALUES (default, 'Drama');
INSERT INTO genres (id, name) VALUES (default, 'Ciencia Ficcion');
INSERT INTO genres (id, name) VALUES (default, 'Comedia');

INSERT INTO years (id, release_year) VALUES (default, 1999);
INSERT INTO years (id, release_year) VALUES (default, 2010);
INSERT INTO years (id, release_year) VALUES (default, 2014);
INSERT INTO years (id, release_year) VALUES (default, 2019);

INSERT INTO actors (id, name) VALUES (default, 'Keanu Reeves');
INSERT INTO actors (id, name) VALUES (default, 'Carrie-Anne Moss');
INSERT INTO actors (id, name) VALUES (default, 'Leonardo DiCaprio');
INSERT INTO actors (id, name) VALUES (default, 'Joseph Gordon-Levitt');
INSERT INTO actors (id, name) VALUES (default, 'Matthew McConaughey');
INSERT INTO actors (id, name) VALUES (default, 'Anne Hathaway');
INSERT INTO actors (id, name) VALUES (default, 'Joaquin Phoenix');

INSERT INTO movies (id, title, synopsis, genre_id, year_id) VALUES
    (default, 'The Matrix', 'Un hacker descubre que la realidad es una simulacion.', 3, 1);
INSERT INTO movies (id, title, synopsis, genre_id, year_id) VALUES
    (default, 'Inception', 'Un ladron entra en los sueños de otras personas para robar secretos.', 3, 2);
INSERT INTO movies (id, title, synopsis, genre_id, year_id) VALUES
    (default, 'Interstellar', 'Un grupo de astronautas busca un nuevo hogar para la humanidad.', 2, 3);
INSERT INTO movies (id, title, synopsis, genre_id, year_id) VALUES
    (default, 'Joker', 'El origen de uno de los villanos mas icononicos del comic.', 2, 4);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 1);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 2);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 3);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 4);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 5);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 6);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (4, 7);