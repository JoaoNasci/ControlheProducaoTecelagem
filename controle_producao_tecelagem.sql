
CREATE database `controle_producao_tecelagem`;
USE `controle_producao_tecelagem` ;

-- drop database  `controle_producao_tecelagem` ;

CREATE TABLE `Endereco` (
  `idEndereco` INT primary key not null auto_increment unique,
  `cep` VARCHAR(45) NOT NULL,
  `rua` VARCHAR(150) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `estado` VARCHAR(3) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(200) NULL
  );

insert into Endereco values (null, 89066345, 'Rua castelo branco', 456, 'Centro', 'Blumenau', 
'SC','BR','Proximo da bera-rio');
select * from Endereco;


CREATE TABLE `Funcionario` (
  `numeroCadastro` int not null primary key unique auto_increment,
  `nome` VARCHAR(45) NOT NULL,
  `Fk_endereco` INT NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(12) NOT NULL unique,
  `dataNascimento` DATE NOT NULL,
  `sexo` VARCHAR(15) NULL,
  `pis` VARCHAR(12) NOT NULL unique,
  `dataAdmissao` DATE NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `turno` VARCHAR(20) NOT NULL,
    FOREIGN KEY (`Fk_endereco`) REFERENCES `Endereco` (`idEndereco`)
    );

insert into Funcionario values (null, 'João Pedro', 1, '(47) 9 32851753', 'test@hotmail',
 '847354575827', 1840-08-02, 'Masculino', '23432345678', now(),'Tecelão III','3ºTurno' );
select * from funcionario;

CREATE TABLE `Cliente` (
  `cnpj` VARCHAR(20) primary key not null unique,
  `nome` VARCHAR(45) NOT NULL,
  `FK_endereco` INT NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `inscricaoEstadual` VARCHAR(45) NOT NULL,
  `nomeFantazia` VARCHAR(150) NOT NULL,
  `ponteEmpresa` VARCHAR(5) NOT NULL,
  `dataFundacao` DATE NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `razaoSocial` VARCHAR(60) NOT NULL,
  `limiteCredito` DOUBLE NOT NULL,
  `ramoAtividade` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_endereco`) REFERENCES `Endereco` (`idEndereco`)
   );


CREATE TABLE `Fornecedor` (
  `cnpj` VARCHAR(20) primary key NOT NULL unique,
  `nome` VARCHAR(45) NOT NULL,
  `FK_endereco` INT NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `inscricaoEstadual` VARCHAR(45) NOT NULL,
  `nomeFantazia` VARCHAR(150) NOT NULL,
  `ponteEmpresa` VARCHAR(5) NOT NULL,
  `dataFundacao` DATE NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `categoriaProduto` VARCHAR(60) NOT NULL,
  `prazoEntregaMedio` INT NOT NULL,
    FOREIGN KEY (`FK_endereco`) REFERENCES `Endereco` (`idEndereco`)
    );



CREATE TABLE `Deposito` (
  `idDeposito` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `FK_endereco` INT NOT NULL,
  `estante` VARCHAR(5) NOT NULL,
  `capacidadeMaxima` DOUBLE NOT NULL,
  `capacidadeAtual` DOUBLE NOT NULL,
    FOREIGN KEY (`FK_endereco`) REFERENCES `Endereco` (`idEndereco`)
    );



CREATE TABLE `Pedido` (
  `idPedido` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `dataAbertura` DATE NOT NULL,
  `previsaoTermino` DATE NOT NULL,
  `producaoTotal` DOUBLE NOT NULL,
  `FK_cliente` VARCHAR(20) NOT NULL,
  `descricao` VARCHAR(100) NULL,
  `status` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_cliente`) REFERENCES `Cliente` (`cnpj`)
   );


CREATE TABLE `Malha` (
  `idMalha` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `FK_fornecedor` VARCHAR(20) NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `peso` DOUBLE NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `largura` DOUBLE NOT NULL,
  `gramatura` INT NOT NULL,
  `tipoTrama` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_fornecedor`) REFERENCES `Fornecedor` (`cnpj`)
   );



CREATE TABLE `Producao` (
  `idProducao` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `FK_pedido` INT NOT NULL,
  `qntPlanejada` VARCHAR(45) NOT NULL,
  `saida` INT NOT NULL,
  `qntProduzida` DOUBLE NOT NULL,
  `qualidade` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_pedido`) REFERENCES `Pedido` (`idPedido`),
    FOREIGN KEY (`saida`) REFERENCES `Malha` (`idMalha`)
    );



CREATE TABLE `Fio` (
  `idFio` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `FK_fornecedor` VARCHAR(20) NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `peso` DOUBLE NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `titulo` VARCHAR(60) NOT NULL,
  `composicao` VARCHAR(60) NOT NULL,
	FOREIGN KEY (`FK_fornecedor`) REFERENCES `Fornecedor` (`cnpj`)
    );



CREATE TABLE `entrada` (
  `ID` INT PRIMARY kEY AUTO_INCREMENT ,
  `Producao_idProducao` INT NOT NULL,
  `Fio_idFio` INT NOT NULL,
    FOREIGN KEY (`Producao_idProducao`) REFERENCES `Producao` (`idProducao`),
    FOREIGN KEY (`Fio_idFio`) REFERENCES `Fio` (`idFio`)
    );



CREATE TABLE `Maquina` (
  `idMaquina` SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NOT NULL,
  `velocidadeRPM` TINYINT NOT NULL,
  `qntVoltas` INT NOT NULL,
  `tempoExecucao` TIME NOT NULL,
  `enficiencia` DOUBLE NOT NULL,
  `FK_Producao` INT NOT NULL,
    FOREIGN KEY (`FK_Producao`) REFERENCES `Producao` (`idProducao`)
   );



CREATE TABLE `Fio_no_Deposito` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Fio_idFio` INT NOT NULL,
  `Deposito_idDeposito` INT NOT NULL,
    FOREIGN KEY (`Fio_idFio`) REFERENCES `Fio` (`idFio`),
    FOREIGN KEY (`Deposito_idDeposito`) REFERENCES `Deposito` (`idDeposito`)
    );



CREATE TABLE `Malha_no_Deposito` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Malha_idMalha` INT NOT NULL,
  `Deposito_idDeposito` INT NOT NULL,
    FOREIGN KEY (`Malha_idMalha`) REFERENCES `Malha` (`idMalha`),
    FOREIGN KEY (`Deposito_idDeposito`) REFERENCES `Deposito` (`idDeposito`)
   );


CREATE TABLE `Operador` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Producao_idProducao` INT NOT NULL,
  `Funcionario_numeroCadastro` int NOT NULL,
    FOREIGN KEY (`Producao_idProducao`) REFERENCES `Producao` (`idProducao`),
    FOREIGN KEY (`Funcionario_numeroCadastro`) REFERENCES `Funcionario` (`numeroCadastro`)
    );

-- alter table Operador modify  `Funcionario_numeroCadastro` int not null;


