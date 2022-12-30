# Base de Dados

O tipo de base de dados utilizada foi o _MySQL_, é _deployed_ em _container Docker_. Para este efeito, criou-se um ficheiro  `docker-compose.yml` para se poder fazer *deploy* de ambos em simultâneo com recurso à ferramenta _Docker Compose_.

Este ficheiro cria um *container* para o tipo de base de dados utilizada, com a palavra-passe `secret2` para o administrador (utilizador `demo`)
</br>

> Para saber criar container, recomenda-se a consulta do ficheiro [BACK-END/readme.md](../readme.md)

</br>

# Pré-requisitos

Pré-requisitos:
- _python_
- _pip_
- _venv_

1. Criar o *virtual enviroment*:
```bash
$ python3 -m venv venv
```
2. Activar o *virtual enviroment*:
```bash
$ source venv/bin/activate
```
3. Instalar as *dependências*:
```bash
$ pip install -r requirements.txt
```
> As dependências que estão no ficheiro `requirements.txt` são o pika, que serve para realizar a conexão com o *MySQL* e o *requests*, que serve para executar pull request à api.

</br>

# **Testar conexão à base de dados**

Para efeitos de teste da conectividade à base de dados, foi criado o script Python que se encontra na pasta **mysql/testMySQL.py**.

Este deve ser executado com os parâmetros user e password.

```bash
# Exemplo para o utilizador 'demo', com palavra-passe 'secret2'
$ python testMysql.py 'demo' 'secret2'
```

O início do output, em caso de sucesso, deve ser semelhante ao disponibilizado abaixo.

```
    Connecting to database...
    <mysql.connector.connection_cext.CMySQLConnection object at 0x7f3a4dc63f70>
    Connected to database
```

A base de dados deverá ficar disponível na porta *33050* do *localhost*.

</br>

# **Populate DB**
**Após ter a aplicação a correr** e ter criado a base de dados e as tabelas, deverà inserir dados na base de dados.
Para este efeito, foi criado um script Python que se encontra na pasta **mysql/data.py**.

Este deve ser executado da seguinte forma:

```bash
$ python data.py 
```
