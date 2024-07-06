
# Email Service

Micro Serviço de envio de emails
Esse serviço está ligado ao serviço de cadastro de usuários, quando um usuário é cadastrado, é enviado um email para endereço passado no cadastro do usuário
Para o funcionamento completo clone o repositório do [**micro serviço de cadasto de usuários**](https://github.com/TorugoMarega/ms-user)



## Autores

- [@Torugo](https://github.com/TorugoMarega)


## Referência
- [Microservices na prática com Java Spring](https://www.youtube.com/watch?v=ZnECi2gatMs)


## Stack utilizada

* **Back-end:** Java, SpringBoot
* **Data:** Mysql
* **Mensageria:** RabbitMQ
* **Documentação de endpoints:** Swagger
* **Logs:** Log4J



## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu application-loc.properties:

### Database:

`spring.datasource.url` - Url do servidor de banco de dados Mysql (Utilizei Mysql, mas se quiser pode alterar o Driver para o banco de dados de sua preferência)

`spring.datasource.username` - Nome do usuário do banco de dados criado para a aplicação

`spring.datasource.password` - Senha do usuário de banco de dados criado para a aplicação

### RabbitMQ
Foi utilizado o [CloudAMQP](https://www.cloudamqp.com/) para a mensageria

`spring.rabbitmq.addresses` - String de conexão do CloudAMQP

`broker.queue.email.name` - Nome da Fila

### Servidor de Email
`spring.mail.host` - Url do servidor de email configurado

`spring.mail.port` - Porta do servidor de email configurado

`spring.mail.username` - Email criado para a aplicação enviar os emails aos clientes

`spring.mail.password` - Senha de aplicação

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/TorugoMarega/ms-email.git
```

Entre no diretório do projeto e abra com sua IDE de preferência

> [!IMPORTANT]
> A aplicação possui ambientes com configurações diferentes dependendo do ambiente de desenvolvimento em que será executado, para executar em ambiente local adicione as VM Options:
> ```
> -Dspring.profiles.active=loc
> ```
> Para executar em outros ambientes basta configurá-los no **pom.xml** e criar um novo arquivo de properties seguindo a estrutura de nome dos outros já existentes na aplicação:
> * **application.properties:** Para configurações globais da aplicação
> * **application-loc.properties:** Para configurações em embiente local da aplicação (máquina do desenvolvedor)
> * **application-dsv.properties:** Para configurações em embiente de desenvolvimento (servidores de desenvolvimento)
> * **application-hml.properties:** Para configurações em embiente de homologação (servidores de homologação)
> * **application-prd.properties:** Para configurações em embiente de produção (servidores de produção)