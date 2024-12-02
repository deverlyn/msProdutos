-- Criação da tabela de instâncias do Job
CREATE TABLE BATCH_JOB_INSTANCE (
                                    JOB_INSTANCE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    VERSION BIGINT,
                                    JOB_NAME VARCHAR(100) NOT NULL,
                                    JOB_KEY VARCHAR(32) NOT NULL,
                                    CONSTRAINT JOB_INST_UN UNIQUE (JOB_NAME, JOB_KEY)
);

-- Criação da tabela de execuções do Job
CREATE TABLE BATCH_JOB_EXECUTION (
                                     JOB_EXECUTION_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     VERSION BIGINT,
                                     JOB_INSTANCE_ID BIGINT NOT NULL,
                                     CREATE_TIME TIMESTAMP NOT NULL,
                                     START_TIME TIMESTAMP,
                                     END_TIME TIMESTAMP,
                                     STATUS VARCHAR(10),
                                     EXIT_CODE VARCHAR(2500),
                                     EXIT_MESSAGE VARCHAR(2500),
                                     LAST_UPDATED TIMESTAMP,
                                     CONSTRAINT JOB_INST_EXEC_FK FOREIGN KEY (JOB_INSTANCE_ID)
                                         REFERENCES BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
);

-- Criação da sequência para execuções do Job
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ START WITH 1 INCREMENT BY 1;

-- Criação da tabela de parâmetros da execução do Job
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS (
                                            JOB_EXECUTION_ID BIGINT NOT NULL,
                                            PARAMETER_NAME VARCHAR(100) NOT NULL,
                                            PARAMETER_TYPE VARCHAR(100) NOT NULL,
                                            PARAMETER_VALUE VARCHAR(2500),
                                            IDENTIFYING CHAR(1) NOT NULL,
                                            CONSTRAINT JOB_EXEC_PARAMS_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                                REFERENCES BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

-- Criação da tabela de execuções de passos
CREATE TABLE BATCH_STEP_EXECUTION (
                                      STEP_EXECUTION_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      VERSION BIGINT NOT NULL,
                                      STEP_NAME VARCHAR(100) NOT NULL,
                                      JOB_EXECUTION_ID BIGINT NOT NULL,
                                      CREATE_TIME TIMESTAMP NOT NULL,
                                      START_TIME TIMESTAMP,
                                      END_TIME TIMESTAMP,
                                      STATUS VARCHAR(10),
                                      COMMIT_COUNT BIGINT,
                                      READ_COUNT BIGINT,
                                      FILTER_COUNT BIGINT,
                                      WRITE_COUNT BIGINT,
                                      READ_SKIP_COUNT BIGINT,
                                      WRITE_SKIP_COUNT BIGINT,
                                      PROCESS_SKIP_COUNT BIGINT,
                                      ROLLBACK_COUNT BIGINT,
                                      EXIT_CODE VARCHAR(2500),
                                      EXIT_MESSAGE VARCHAR(2500),
                                      LAST_UPDATED TIMESTAMP,
                                      CONSTRAINT JOB_EXEC_STEP_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                          REFERENCES BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

-- Criação da tabela de contexto da execução de passos
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT (
                                              STEP_EXECUTION_ID BIGINT PRIMARY KEY,
                                              SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                              SERIALIZED_CONTEXT CLOB,
                                              CONSTRAINT STEP_EXEC_CTX_FK FOREIGN KEY (STEP_EXECUTION_ID)
                                                  REFERENCES BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
);

-- Criação da tabela de contexto da execução do Job
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT (
                                             JOB_EXECUTION_ID BIGINT PRIMARY KEY,
                                             SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                             SERIALIZED_CONTEXT CLOB,
                                             CONSTRAINT JOB_EXEC_CTX_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                                 REFERENCES BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);
CREATE SEQUENCE IF NOT EXISTS BATCH_JOB_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS BATCH_JOB_EXECUTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS BATCH_STEP_EXECUTION_SEQ START WITH 1 INCREMENT BY 1;