# METAS CONSULTORIA
A Metas Consultoria é uma empresa que presta serviços de consultoria em gestão pública, focando principalmente na área da saúde. A empresa atua na gestão de Secretarias de Saúde em municípios, abrangendo tanto a atenção especializada (hospitais) quanto a atenção primária (unidades básicas de saúde). Entre as atividades principais estão o planejamento orçamentário e de ações para implementação de projetos como construção de hospitais, ampliação de serviços de saúde, e projetos de expansão de unidades de saúde.

Além disso, a consultoria inclui a captação de projetos e recursos, analisando portarias e publicações ministeriais para identificar oportunidades de financiamento para aquisição de equipamentos e serviços necessários aos municípios. Os clientes da Metas Consultoria geralmente são prefeitos e gestores municipais, que contratam os serviços para planejamento, execução e monitoramento de ações ao longo do mandato.

# HUB METAS CONSULTORIA
Nossa equipe desenvolveu um sistema de acompanhamento de projetos para a Metas Consultoria, apresentando-o como um MVP (Produto Mínimo Viável). Neste repositório, você encontrará todos os arquivos relacionados ao projeto.

Para testar o repositório localmente, siga as instruções detalhadas no arquivo README.

## FERRAMENTAS UTILIZADAS
- **IDEs**: VS Code, IntelliJ IDEA, DBeaver, DataGrip, WebStorm
- **Linguagens**: Java 17, MySQL, JavaScript, HTML, CSS
- **Frameworks**: React
- **Conexão com Banco de Dados**: JDBC
- **Outras Ferramentas**: Docker, Maven

## PRÉ-REQUISITOS
1. Instalar o [Java](https://www.oracle.com/br/java/).
2. Instalar o [Docker](https://docs.docker.com/desktop/install/windows-install/).

Clique nos links acima para acessar a documentação oficial e seguir as instruções de instalação.

# INSTALANDO O REPOSITÓRIO LOCALMENTE

Abra o terminal e execute o comando para clonar o repositório:

```bash
git clone https://github.com/SofiaValadares/MetasConsultoria
```

Depois de clonar, navegue até a pasta do projeto:

```bash
cd MetasConsultoria
```

Certifique-se de instalar todas as dependências necessárias antes de prosseguir.

# EXECUTANDO O PROJETO LOCALMENTE
### 1. Configuração do Banco de Dados
Certifique-se de ter o Docker aberto com permissão de administrador e aguarde a Docker Engine iniciar.

Para criar a imagem do banco de dados, execute:

```bash
docker run -d --name mysql_metas_container -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3307:3306 mysql:8.0
```

Verifique o status da imagem com:

```bash
docker ps
```

Para parar o container, use:

```bash
docker stop mysql_metas_container
```

Para reiniciá-lo, execute:

```bash
docker start mysql_metas_container
```

E para remover o container:

```bash
docker rm mysql_metas_container
```

### 2. Executando o Back-End
Compile o projeto com:

```bash
mvn clean package
```

Em seguida, inicie o projeto:

```bash
mvn spring-boot:run
```
