drop database if exists football_manager;
create database if not exists football_manager;
use football_manager;

drop table if exists teams;
drop table if exists players;
drop table if exists transfers;

create table teams
(
	id int primary key auto_increment,
	name varchar(40),
    country varchar(40),
    town varchar(40),
    balance int
);

create table players
(
	id int primary key auto_increment,
    first_name varchar(40),
    last_name varchar(40),
    birth_date date,
    start_career date,
    team_id int,
    foreign key (team_id) references teams (id) on delete cascade
);

create table transfers 
(
	id int primary key auto_increment,
    player_id int,
    team_to int, 
    transfer_date date,
    foreign key (player_id) references players (id) on delete cascade,
	foreign key (team_to) references teams (id) on delete cascade
);
