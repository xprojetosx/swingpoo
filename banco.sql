CREATE DATABASE `cadastro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `pessoa` (
  `cpf` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `data_nasc` varchar(45) NOT NULL,
  `sexo` varchar(15) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
