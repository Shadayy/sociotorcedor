CREATE TABLE SOCIO (ID BIGINT IDENTITY(1,1) PRIMARY KEY ,
						NOME VARCHAR(255),
						EMAIL VARCHAR(255),
					 	DATANASCIMENTO BIGINT,
					 	IDTIMEDOCORACAO BIGINT
					 	);

CREATE TABLE SOCIOCAMPANHA (IDSOCIO BIGINT,
					 	IDCAMPANHA BIGINT,
					 	IDTIMEDOCORACAO BIGINT,
					 	PRIMARY KEY (IDSOCIO, IDCAMPANHA, IDTIMEDOCORACAO)
					 	);

ALTER TABLE SOCIOCAMPANHA ADD CONSTRAINT FK_SOCIO_CAMPANHA FOREIGN KEY (IDSOCIO) REFERENCES SOCIO(ID);
