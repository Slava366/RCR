create table EMPLOYEES (
    ID integer auto_increment PRIMARY KEY,
    FIO varchar(254) UNIQUE NOT NULL
);

create table POSITIONS (
    ID integer auto_increment PRIMARY KEY,
    NAME varchar(32) UNIQUE NOT NULL
);

create table REPORTS (
    ID integer auto_increment PRIMARY KEY,
    E_FIO varchar(254) NOT NULL,
    P_NAME varchar(32) NOT NULL,
    F_DATE timestamp NOT NULL,
    T_DATE timestamp NOT NULL,
    REASON varchar(254) NOT NULL
);