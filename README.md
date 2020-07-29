# mylist

Projeto com objetivo de contemplar um conjunto completo de tecnologias a fim de criar uma aplicação frontend consumindo microserviços no backend.
Também contempla todo o processo de build e configurações necessárias para instalar esse conjunto de aplicações em um cluster kubernetes com um service mesh instalado (Istio).

O resultado final no frontend permite gerenciar sua lista de pendências para realizar, vide imagens abaixo:
![alt text](https://github.com/gbrandao07/mylist/blob/master/utils/imgs/full-app.png?raw=true)
<img src="https://github.com/gbrandao07/mylist/blob/master/utils/imgs/edit-card.png?raw=true" width="415" height="220" />
<img src="https://github.com/gbrandao07/mylist/blob/master/utils/imgs/new-card.png?raw=true" width="415" height="220" />

## Tecnologias

- Java 8+
- Maven
- Spring framework (com starters do Spring boot e Spring Cloud Feign)
- Docker
- Kubernetes 
- Istio

## Projetos

### webapp-mylist

Projeto feito com Angular 9 para contemplar o frontend.

### card-service

Microserviço responsável por consolidar todos os cards cadastrados pelo frontend, independente do estado do card.

### todo-service

O frontend invoca este serviço para:

- Criar um card que, por sua vez, cria o card no card-service. Após sucesso, o mesmo faz um cache no seu próprio banco de dados.
O objetivo é ter independência entre os microserviços de forma que, se os outros ficarem indisponíveis, o todo-service consiga exibir os cards na sua coluna.

- Mover um card para 'doing'

- Exibir os cards na coluna 'todo'

### doing-service

O frontend invoca este serviço para:

- Mover um card para 'done'

- Voltar um card para 'todo'

- Exibir os cards na coluna 'doing'

### done-service

O frontend invoca este serviço para:

- Exibir os cards na coluna 'doing'

## Arquitetura
![alt text](https://github.com/gbrandao07/mylist/blob/master/utils/imgs/mylist-projects.png?raw=true)

// TODO explicar sobre o k8s, istio e relacao entre os projetos

## Service mesh gerada pelo Kiali
![alt text](https://github.com/gbrandao07/mylist/blob/master/utils/imgs/service-mesh-live.png?raw=true)


