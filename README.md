# **Serviço Checku**

Para construir esta aplicação recorreu-se à *framework* _Spring Boot_.
</br>

# **Criar containers**
Para criar os *containers*, o ficheiro com as configurações deve ser validado.

```bash
    $ docker compose config
```

Se não forem mostrados erros, pode avançar-se com a sua criação.

```bash
    $ docker compose up -d
```

> Para saber como popular a base de dados, recomenda-se a consulta do ficheiro [back-end/DBInsert/readme.md](DBInsert/readme.md).

</br>


# **Correr Aplicação**

Antes de correr a aplicação, é necessário garantir que as bases de dados _MySQL_ e _Rabbitmq_ estão a correr e disponíveis nas respetivas portas. É também necessário verificar que o _Spring Boot_ está a utilizar os _urls_ corretos destes serviços em `application.properties`.

Ainda é necessário colocar a *Geração de dados* a funcionar. Para tal, recomenda-se a consulta do ficheiro [data-generator/README.md](https://github.com/Cheku-Corporation/data-generator/blob/main/README.md), a secção _Execução_, e escolher umas das formas de execução.

Uma vez assegurada esta condição, basta executar o comando abaixo para correr a aplicação.

```bash
$ ./mvnw spring-boot:run
```

Se a compilação não foi interrompida por nenhum erro, esta deve ficar disponível na porta `8080` do `localhost`.

</br>

# Preparar aplicação para o _Deploy_


Antes de ser possível fazer o _deploy_ deve-se alterar os _urls_ dos serviços no ficheiro `application.properties` de modo a estarem associados ao seu nome no _Docker Compose_.

```properties
    spring.datasource.url=jdbc:mysql://mysql:3306/checku
    spring.rabbitmq.host=rabbitmq
```

</br>




