#!/bin/bash

# Cria cards. 
# Por padrao, cards sempre criados em "TODO"
# Entao chama a criacao via "TODO". O todo-service ira criar o card via card-service com todos os detalhes e, tendo o id, em maos, ira fazer um cache no seu banco local

curl -X POST -F 'name=bug 1' -F 'authorName=gb' -F 'description=desc1'  http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 2' -F 'authorName=gb' -F 'description=desc2'  http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 3' -F 'authorName=gb' -F 'description=desc3'  http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 4' -F 'authorName=gb' -F 'description=desc4'  http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 5' -F 'authorName=gb' -F 'description=desc5'  http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1

# Obtem a lista de card na coluna TODO (para gerar trafego na mesh apenas)
curl http://localhost:30080/mylist/todo/cards
echo -e ' '
sleep .1

# Move some cards from TODO to DOING to generate traffic in the mesh
curl -X POST http://localhost:30080/mylist/todo/cards/1/move
echo -e ' '
sleep .1
curl -X POST http://localhost:30080/mylist/todo/cards/2/move
echo -e ' '
sleep .1


# Move some cards back from DOING to TODO to generate traffic in the mesh
curl -X POST http://localhost:30080/mylist/doing/cards/1/move?to=todo
echo -e ' '
sleep .1


# Move some cards from DOING to DONE to generate traffic in the mesh
curl -X POST http://localhost:30080/mylist/doing/cards/2/move?to=done
echo -e ' '
sleep .1
