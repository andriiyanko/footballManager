-- Insert data to tables--
insert teams(name, country, town , balance)
values
('Barcelona', 'Spain', 'Barcelona', 300000000),
('Real Madrid', 'Spain', 'Madrid', 500000000),
('Dynamo Kyiv', 'Ukraine', 'Kyiv', 100000000),
('Genoa', 'Italy', 'Genoa', 100000000),
('Bayern Munich', 'Germany', 'Munich', 400000000);

insert players( first_name, last_name, birth_date , start_career, team_id )
values
('Lionel', 'Messi', '1985-09-11', '2005-10-10', 1),
('Gerar', 'Pique', '1985-10-11', '2005-11-10', 1),
('Deco', 'Rodriguez', '1990-11-11', '2009-10-10', 1),

('Iker', 'Casilias', '1991-10-11', '2010-08-10', 2),
('Cristiano', 'Ronaldo', '1985-07-20', '2005-02-15', 2),
('Karim', 'Benzema', '1986-09-11', '2006-05-05', 2),

('Oleksandr', 'Shovkovskyi', '1990-06-15', '2008-07-10', 3),
('Andrii', 'Shevchenko', '1990-12-11','2008-08-21', 3),
('Oleg', 'Gusev', '1991-09-11', '2009-10-10', 3),

('Philipe', 'Lam', '1990-05-15', '2008-08-10', 5),
('Robert', 'Levandovski', '1992-10-10', '2011-10-11', 5),

('Philipo', 'Indzahi', '1989-09-21', '2007-10-20', 4),
('Diego', 'Maradona', '1990-09-21', '2007-10-20', 4);

insert transfers ( player_id, team_to , transfer_date)
values
(1, 1, '2008-10-10'),
(8, 3, '2010-08-15');  
