# README - Projeto Metas Consultoria

Este documento fornece um guia detalhado para instalar o Docker, configurar um container MySQL e gerenciá-lo. Essas instruções ajudarão a garantir que o ambiente esteja pronto para o desenvolvimento e testes do projeto **Metas Consultoria**.

## Pré-requisitos

Antes de começar, verifique se você tem os seguintes pré-requisitos:

- **Sistema Operacional**: Windows, macOS ou uma distribuição Linux compatível (Ubuntu, Debian, etc.).
- **Acesso de Administrador/Sudo**: Para instalar e gerenciar o Docker.

## Instalação do Docker

### 1. Instruções para Ubuntu/Linux

Para usuários de sistemas baseados em Debian (Ubuntu, por exemplo), siga as instruções abaixo:

1. **Atualize os pacotes existentes**:
   ```bash
   sudo apt update
   ```

2. **Instale dependências necessárias**:
   ```bash
   sudo apt install apt-transport-https ca-certificates curl software-properties-common
   ```

3. **Adicione a chave GPG oficial do Docker**:
   ```bash
   curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
   ```

4. **Adicione o repositório Docker**:
   ```bash
   echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
   ```

5. **Instale o Docker**:
   ```bash
   sudo apt update
   sudo apt install docker-ce
   ```

6. **Adicione seu usuário ao grupo Docker** (para evitar usar `sudo` em comandos Docker):
   ```bash
   sudo usermod -aG docker $USER
   newgrp docker
   ```

### 2. Instruções para Windows e macOS

1. **Baixe o Docker Desktop**:
   - Acesse o [site oficial do Docker Desktop](https://www.docker.com/products/docker-desktop) e baixe a versão apropriada para o seu sistema operacional.

2. **Instale o Docker Desktop**:
   - Siga as instruções de instalação e permita que o Docker faça as configurações necessárias no sistema.

3. **Reinicie o computador** (se solicitado) após a instalação.

4. **Verifique se o Docker está instalado corretamente**:
   ```bash
   docker --version
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
