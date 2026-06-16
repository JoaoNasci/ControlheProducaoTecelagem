
CREATE database `controle_producao_tecelagem`;
USE `controle_producao_tecelagem` ;

-- drop database  `controle_producao_tecelagem` ;




CREATE TABLE `Login`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`email` VARCHAR(128) NOT NULL UNIQUE,
`senha` VARCHAR(45) NOT NULL
);

INSERT into `Login` (email,senha) values ( "test@hotmail", "test123456");
INSERT into `Login` (email,senha) values ( "test2@hotmail", "test123456");

select * from Login;

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
  `numeroCadastro` int not null primary key unique,
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
    
insert into Funcionario values (0435, 'João Pedro', 1, '(47) 9 32851753', 'test@hotmail',
 '847354575827', '1840-08-02', 'Masculino', '23432345678', now(),'Tecelão III','3ºTurno' );
select * from funcionario ;

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
   
INSERT INTO Cliente VALUES ('12345678000199', 'Malhas Blumenau Ltda', 1, '(47) 3322-1111', 'compras@malhasblumenau.com',
 '123456789', 'Malhas Blumenau', 'M', '2015-04-10', 'Ativo', 'Malhas Blumenau Indústria e Comércio S/A', 50000.00, 'Confecção');
 select * from Cliente;
 
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

INSERT INTO Fornecedor VALUES 
('98765432000188', 'Fiação Vale do Itajaí', 1, '(47) 3333-2222', 'vendas@fiacaovale.com', '987654321', 
'Fiação Vale', 'G', '2010-08-25', 'Ativo', 'Fios de Algodão e Poliéster', 5);
select * from Fornecedor;

CREATE TABLE `Deposito` (
  `idDeposito` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) UNIQUE NOT NULL ,
  `FK_endereco` INT NOT NULL,
  `capacidadeMaxima` DOUBLE NOT NULL,
  `capacidadeAtual` DOUBLE NOT NULL,
    FOREIGN KEY (`FK_endereco`) REFERENCES `Endereco` (`idEndereco`)
    );

INSERT INTO Deposito VALUES (null,'Depósito Central de Fios', 1, 1000000.00, 0.00);
select * from Deposito;

CREATE TABLE `Pedido` (
  `idPedido` varchar(45) PRIMARY KEY NOT NULL,
  `dataAbertura` DATE NOT NULL,
  `previsaoTermino` DATE NOT NULL,
  `producaoTotal` DOUBLE NOT NULL,
  `FK_cliente` VARCHAR(20)  NOT NULL,
  `descricao` VARCHAR(100) NULL,
  `status` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_cliente`) REFERENCES `Cliente` (`cnpj`)
   );

INSERT INTO Pedido  VALUES ('GH-675','2026-06-01', '2026-06-15', 1500.00, '12345678000199', 
'Pedido de malha moletom para coleção de inverno', 'Em Produção');
select * from Pedido;

CREATE TABLE `Malha` (
  `idMalha` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `loteMalha` varchar(45) not null,
  `FK_fornecedor` VARCHAR(20) NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `peso` DOUBLE NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `largura` DOUBLE NOT NULL,
  `gramatura` INT NOT NULL,
  `tipoTrama` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`FK_fornecedor`) REFERENCES `Fornecedor` (`cnpj`)
   );

INSERT INTO Malha VALUES (null,'89TY','98765432000188', 'Azul Marinho', 25.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');
INSERT INTO Malha VALUES (null,'89TY','98765432000188', 'Azul Marinho', 25.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');
INSERT INTO Malha VALUES (null,'88TY','98765432000188', 'Azul Marinho', 10.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');
INSERT INTO Malha VALUES (null,'88TY','98765432000188', 'Azul Marinho', 13.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');
INSERT INTO Malha VALUES (null,'89TY','98765432000188', 'Azul Marinho', 25.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');
INSERT INTO Malha VALUES (null,'89TY','98765432000188', 'Azul Marinho', 25.00, 'Malha Piquet Premium', 1.80, 220, 'Tubular');

select * from Malha where loteMalha = '89TY' ;

CREATE TABLE `Producao` (
  `idProducao` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `FK_pedido` varchar(45) NOT NULL,
  `qntPlanejada` double NOT NULL,
  `saidaMateriaPrima` INT NOT NULL,
  `qntProduzida` DOUBLE NOT NULL,
  `qualidade` smallint NOT NULL,
    FOREIGN KEY (`FK_pedido`) REFERENCES `Pedido` (`idPedido`),
    FOREIGN KEY (`saidaMateriaPrima`) REFERENCES `Malha` (`idMalha`)
    );

INSERT INTO Producao VALUES (null,'GH-675', 1500 , 1, 45.00, 2);
INSERT INTO Producao VALUES (null,'GH-675', 1800 , 1, 20.00, 1);

select * from Producao;

CREATE TABLE `Operador` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Producao_idProducao` INT NOT NULL,
  `Funcionario_numeroCadastro` int NOT NULL,
    FOREIGN KEY (`Producao_idProducao`) REFERENCES `Producao` (`idProducao`),
    FOREIGN KEY (`Funcionario_numeroCadastro`) REFERENCES `Funcionario` (`numeroCadastro`)
    );
select * From operador;
insert into operador values (null,1,0435);



CREATE TABLE `Fio` (
  `idFio` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `loteFio` varchar(45) not null unique,
  `FK_fornecedor` VARCHAR(20) NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `peso` DOUBLE NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `titulo` VARCHAR(60) NOT NULL,
  `composicao` VARCHAR(60) NOT NULL,
	FOREIGN KEY (`FK_fornecedor`) REFERENCES `Fornecedor` (`cnpj`)
    );

INSERT INTO Fio VALUES (null,'144NJK','98765432000188', 'Cru / Branco', 24000.00, 'Fio Ne 30/1 Penteado', '30/1', '100% Algodão');
INSERT INTO Fio VALUES (null,'144LKJ','98765432000188', 'Cru / Branco', 20000.00, 'Fio Ne 30/1 Penteado', '30/1', '100% Algodão');
INSERT INTO Fio VALUES (null,'144KNC','98765432000188', 'Cru / Branco', 2400.00, 'Fio Ne 30/1 Penteado', '30/1', '100% Algodão');

select * from Fio;

    
CREATE TABLE `entradaMateriaPrima` (
  `ID` INT PRIMARY kEY AUTO_INCREMENT ,
  `Producao_idProducao` INT NOT NULL,
  `Fio_idFio` INT NOT NULL,
    FOREIGN KEY (`Producao_idProducao`) REFERENCES `Producao` (`idProducao`),
    FOREIGN KEY (`Fio_idFio`) REFERENCES `Fio` (`idFio`)
    );

INSERT INTO entradaMateriaPrima VALUES (null,1, 1);
INSERT INTO entradaMateriaPrima VALUES (null,1, 2);
INSERT INTO entradaMateriaPrima VALUES (null,1, 3);

select * from entradaMateriaPrima;

CREATE TABLE `Maquina` (
  `idMaquina` SMALLINT PRIMARY KEY NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NOT NULL,
  `velocidadeRPM` TINYINT NOT NULL,
  `qntVoltas` INT NOT NULL,
  `tempoExecucao` TIME NOT NULL,
  `enficiencia` DOUBLE NOT NULL,
  `FK_Producao` INT ,
    FOREIGN KEY (`FK_Producao`) REFERENCES `Producao` (`idProducao`)
   );

INSERT INTO Maquina VALUES (23,'Terrot S296', 'Circular Monofonte', 'Trabalhando', 22, 180000, '06:30:00', 92.5, 1);
INSERT INTO Maquina VALUES (18,'Terrot S295', 'Circular Monofonte', 'Trabalhando', 20, 1800, '06:30:00', 96.5, null);

select * from Maquina;

CREATE TABLE `Fio_no_Deposito` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Fio_idFio` INT NOT NULL,
  `Deposito_idDeposito` INT NOT NULL,
  `estante_Fio` VARCHAR(5) NOT NULL,
    FOREIGN KEY (`Fio_idFio`) REFERENCES `Fio` (`idFio`),
    FOREIGN KEY (`Deposito_idDeposito`) REFERENCES `Deposito` (`idDeposito`)
    );
    
INSERT INTO Fio_no_Deposito VALUES (null,1, 1,'A02');
INSERT INTO Fio_no_Deposito VALUES (null,3, 1,'A02');

select * from Fio_no_Deposito;

CREATE TABLE `Malha_no_Deposito` (
  `ID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Malha_idMalha` INT NOT NULL,
  `Deposito_idDeposito` INT NOT NULL,
  `estante_Malha` VARCHAR(5) NOT NULL,
    FOREIGN KEY (`Malha_idMalha`) REFERENCES `Malha` (`idMalha`),
    FOREIGN KEY (`Deposito_idDeposito`) REFERENCES `Deposito` (`idDeposito`)
   );
   
INSERT INTO Malha_no_Deposito VALUES (null,1, 1,'A01');
INSERT INTO Malha_no_Deposito VALUES (null,4, 1,'A02');
INSERT INTO Malha_no_Deposito VALUES (null,6, 1,'A03');

select * from Malha_no_Deposito;



select  p.idProducao as Producao, p.FK_pedido as Pedido , p.qntPlanejada as Quantidade_Planejada, 
p.saidaMateriaPrima as ID_Malha, p.qntProduzida as Quantidade_Produzida,
 p.qualidade as Qualidade, o.Funcionario_numeroCadastro as Operador,
 ma.idMaquina as Maquina, f.Fio_idFio as Fio
from Producao p right join Pedido ped on p.FK_pedido = ped.idPedido
right join Malha m on p.saidaMateriaPrima = m.idMalha
inner join entradaMateriaPrima f on p.idProducao = f.Producao_idProducao
right join Maquina ma on p.idProducao = ma.FK_Producao
right join Operador o on p.idProducao = o.Producao_idProducao
where  p.FK_pedido = 'GH-675' or p.idProducao = '2';

 SELECT 
   p.idProducao as Producao, p.FK_pedido AS Pedido, p.qntPlanejada AS Quantidade_Planejada, 
    p.saidaMateriaPrima AS ID_Malha, p.qntProduzida AS Quantidade_Produzida, 
    p.qualidade AS Qualidade, o.Funcionario_numeroCadastro AS Operador 
    FROM Producao p 
    RIGHT JOIN Pedido ped ON p.FK_pedido = ped.idPedido 
    RIGHT JOIN Operador o ON p.idProducao = o.Producao_idProducao 
    WHERE p.idProducao = '1';


 
 
 SELECT d.nome, d.capacidadeMaxima, e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais,
 e.complemento, f.estante_Fio, fi.loteFio AS lote, fi.FK_fornecedor, fi.cor, fi.peso, fi.descricao, fi.titulo AS campo1,
 fi.composicao AS campo2, NULL AS campo3, 'Fio' AS tipoItem FROM Fio_no_Deposito f 
 INNER JOIN Deposito d ON f.Deposito_idDeposito = d.idDeposito INNER JOIN Fio fi ON f.Fio_idFio = fi.idFio 
 INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco 
 UNION ALL 
 SELECT d.nome, d.capacidadeMaxima, e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais, e.complemento,
 m.estante_Malha, ma.loteMalha AS lote, ma.FK_fornecedor, ma.cor, ma.peso, ma.descricao, ma.largura AS campo1, 
 ma.gramatura AS campo2, ma.tipoTrama AS campo3, 'Malha' AS tipoItem FROM Malha_no_Deposito m
 INNER JOIN Deposito d ON m.Deposito_idDeposito = d.idDeposito INNER JOIN Malha ma ON m.Malha_idMalha = ma.idMalha 
 INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco;