CREATE TABLE DCOM_JOB (
    JOB_NAME character varying(255) PRIMARY KEY,
    LAST_RESULT character varying(255) NOT NULL UNIQUE,
    LAST_EXECUTED timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    NEXT_EXECUTION timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO DCOM_JOB (JOB_NAME, LAST_RESULT) VALUES 
('checkForNewMessages', 'successfull');

