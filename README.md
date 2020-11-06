# Aplicação de controle de vacinas para covid-19: checklist

## Tela principal

![Tela principal](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/main.jpg)

- [x] Menus PESSOAS, VACINAS e APLICAÇÕES, com submenus "Cadastrar" e "Consultar"
- [x] Painel de cadastro de PESSOAS
- [x] Painel de cadastro de VACINAS
- [ ] Painel de cadastro de APLICAÇÕES
- [ ] Painel para consultar com filtros, editar e excluir PESSOAS
- [ ] Painel para consultar com filtros, editar e excluir VACINAS
- [ ] Painel para consultar com filtros, editar e excluir APLICAÇÕES
- [ ] Painel inicial com dashboard de resumos de relatórios
- [ ] Prototipação das telas e definição das regras de negócio para o dia 11 (pode ser via arquivo JAR, com as regras em PDF)

## Cadastro de Pessoas

![Cadastro de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/pessoas.jpg)

- [x] Campo "Instituição" só aparece caso o checkbox "Pesquisador" esteja selecionado
- [x] Validações dos campos (inclusive data)

## Cadastro de Vacinas

![Cadastro de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/vacina.jpg)

- [x] Combobox via banco de dados para selecionar estágio e pesquisador
- [x] Combobox via array Java para selecionar país de origem
- [x] Validações dos campos (inclusive data)

## Regras de Negócio

* Uma vacina pode ser aplicada em pessoas conforme a fase: 1 - Somente pesquisadores; 2 - Voluntários; 3 - Público em geral

* Cada pessoa pode tomar apenas uma vacina por ano

* Todas as pessoas devem possuir CPF válido com 11 caracteres  numéricos (OK)

* Todas as pessoas devem possuir nome e um sobrenome com pelo menos três caracteres (OK)

* Não podem ser cadastradas duas pessoas com o mesmo CPF 

* Outras?

## Banco de dados

    SET SQL_SAFE_UPDATES = 0;
    DROP DATABASE IF EXISTS DBVACINA;
    CREATE DATABASE DBVACINA;
    USE DBVACINA;

    CREATE TABLE PESSOA (
      IDPESSOA INT NOT NULL AUTO_INCREMENT
        , NOME VARCHAR (100) NOT NULL
        , DT_NASCIMENTO DATE
        , SEXO ENUM ('M', 'F')
        , CPF VARCHAR (11)
        , VOLUNTARIO BOOLEAN
        , PRIMARY KEY (IDPESSOA)
    );

    CREATE TABLE PESQUISADOR (
      IDPESQUISADOR INT NOT NULL AUTO_INCREMENT
        , NOME VARCHAR (100)
        , IDPESSOA INT
        , INSTITUICAO VARCHAR (256)
        , PRIMARY KEY (IDPESQUISADOR)
    );

    CREATE TABLE VACINA (
      IDVACINA INT NOT NULL AUTO_INCREMENT
        , IDPESQUISADOR INT NOT NULL
        , PAIS_ORIGEM VARCHAR(45)
        , ESTAGIO_PESQUISA ENUM ('1', '2', '3')
      , DT_INICIO DATE     
        , PRIMARY KEY (IDVACINA)
        , FOREIGN KEY (IDPESQUISADOR) REFERENCES PESQUISADOR (IDPESQUISADOR)
    );

    CREATE TABLE VACINACAO (
      IDVACINACAO INT NOT NULL AUTO_INCREMENT
        , IDVACINA INT NOT NULL
        , IDPESSOA INT NOT NULL
        , DT_VACINACAO DATE
        , AVALIACAO ENUM ('1', '2', '3', '4', '5')
        , PRIMARY KEY (IDVACINACAO)
        , FOREIGN KEY (IDVACINA) REFERENCES VACINA (IDVACINA)
        , FOREIGN KEY (IDPESSOA) REFERENCES PESSOA (IDPESSOA)
    );
