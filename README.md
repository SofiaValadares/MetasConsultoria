# METAS CONSULTORIA
A Metas Consultoria é uma empresa que presta serviços de consultoria em gestão pública, focando principalmente na área da saúde. A empresa atua na gestão de Secretarias de Saúde em municípios, abrangendo tanto a atenção especializada (hospitais) quanto a atenção primária (unidades básicas de saúde). Entre as atividades principais estão o planejamento orçamentário e de ações para implementação de projetos como construção de hospitais, ampliação de serviços de saúde, e projetos de expansão de unidades de saúde.

Além disso, a consultoria inclui a captação de projetos e recursos, analisando portarias e publicações ministeriais para identificar oportunidades de financiamento para aquisição de equipamentos e serviços necessários aos municípios. Os clientes da Metas Consultoria geralmente são prefeitos e gestores municipais, que contratam os serviços para planejamento, execução e monitoramento de ações ao longo do mandato.

# HUB METAS CONSULTORIA
Nossa equipe desenvolveu um sistema de acompanhamento de projetos para a Metas Consultoria, apresentando-o como um MVP (Produto Mínimo Viável). Neste repositório, você encontrará todos os arquivos relacionados ao projeto.

Para testar o repositório localmente, siga as instruções detalhadas no arquivo README.

## Feramentas Ultilizadas
IDEs: VS Code, IntelliJ IDEA, DBearver, Data Grip, WebStorm.
Linguagen: Java, MySQL, Javascript, HTML e CSS
Framework: React
Conexão com Banco de Dados: JBDC

## Pré-requisitos
1. Ter o [Java](https://www.oracle.com/br/java/) instalado em sua máquina.
2. Ter o [Docker](https://docs.docker.com/desktop/install/windows-install/) instalado em sua máquina.

Para instalar qualquer um dos pré-requisitos, basta clicar no nome destacado em azul para ser redirecionado à documentação oficial da ferramenta.

# INSTALANDO REPOSITORIO NA MAQUINA
Abra seu terminal e execulte o seguinte comando para clonar o repositorio na sua maquina

     ```bash
     git clone https://github.com/SofiaValadares/MetasConsultoria
     ```

Com os arquivos na sua maquina navegue ate a pasta do projeto

     ```bash
     cd ./MetasConsultoria
     ```


### 1. Comando para Baixar e Executar o Container MySQL

Após a instalação do Docker, siga estas etapas para criar e rodar um container MySQL que será usado pelo projeto. Use o comando abaixo:

```bash
docker run -d --name mysql_metas_container -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 mysql:8.0
```

**Explicação do comando**:
- `-d`: Executa o container em segundo plano, permitindo que você continue usando o terminal para outros comandos.
- `--name mysql_metas_container`: Define um nome específico para o container, facilitando a identificação e o gerenciamento.
- `-e MYSQL_ROOT_PASSWORD=my-secret-pw`: Define a senha do usuário `root` do MySQL. Esta senha é necessária para acesso administrativo ao banco de dados e **não deve ser alterada** para garantir a compatibilidade com o código existente do projeto.
- `-p 3306:3306`: Mapeia a porta 3306 do container (onde o MySQL está ouvindo) para a porta 3306 do host (sua máquina local). **Certifique-se de que a porta 3306 esteja livre** para evitar conflitos.
- `mysql:8.0`: Especifica a imagem do MySQL que será usada. A versão `8.0` é compatível com o projeto e deve ser mantida.

**Importante**:
- As variáveis como a senha `my-secret-pw` e a porta `3306` não devem ser modificadas, pois são necessárias para que a aplicação se conecte corretamente ao banco de dados sem ajustes adicionais no código.

**Dica**: Se o container já existir com outro nome ou configuração, remova-o antes de executar o comando acima para evitar conflitos.


### 2. Aguarde a Inicialização

É recomendável esperar cerca de 20 segundos para garantir que o MySQL tenha iniciado completamente.

### 3. Verifique o Status do Container

Para confirmar que o container está em execução, use o comando:

```bash
docker ps
```

Este comando exibirá uma lista dos containers em execução. Verifique se `mysql_metas_container` está listado e com o status `Up`.

## Parando e Removendo o Container

### Parar o Container

Se você quiser parar o container MySQL, use o seguinte comando:

```bash
docker stop mysql_metas_container
```

### Remover o Container

Para remover o container depois de pará-lo:

```bash
docker rm mysql_metas_container
```

## Verificação de Logs e Problemas

Se você encontrar problemas ou quiser ver o que está acontecendo no container, use:

```bash
docker logs mysql_metas_container
```

Este comando mostrará os logs do MySQL, o que pode ajudar a diagnosticar problemas.

## Notas Finais

- Certifique-se de que a porta `3306` não esteja em uso por outro serviço para evitar conflitos de conexão.
- Se você precisar rodar comandos SQL diretamente, pode acessar o MySQL com:
  ```bash
  docker exec -it mysql_metas_container mysql -u root -pmy-secret-pw
  ```

Com essas instruções, você estará pronto para configurar e gerenciar um container MySQL em seu ambiente de desenvolvimento.
