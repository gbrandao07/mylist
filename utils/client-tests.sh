#!/bin/bash

# configs
hostname=localhost
cardId1=1
cardId2=2
nodePort=30080


# Create cards. 
# By default, all cards will be created in "TODO"
curl -X POST -F 'name=bug 1' -F 'authorName=gb' -F 'description=desc1'  http://$hostname:$nodePort/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 2' -F 'authorName=gb' -F 'description=desc2'  http://$hostname:$nodePort/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 3' -F 'authorName=gb' -F 'description=desc3'  http://$hostname:$nodePort/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 4' -F 'authorName=gb' -F 'description=desc4'  http://$hostname:$nodePort/mylist/todo/cards
echo -e ' '
sleep .1
curl -X POST -F 'name=bug 5' -F 'authorName=gb' -F 'description=desc5'  http://$hostname:$nodePort/mylist/todo/cards
echo -e ' '
sleep .1


# Move some cards from TODO to DOING to generate traffic in the mesh
curl -X POST http://$hostname:$nodePort/mylist/todo/cards/$cardId1/move
echo -e ' '
sleep .1
curl -X POST http://$hostname:$nodePort/mylist/todo/cards/$cardId2/move
echo -e ' '
sleep .1


# Move some cards back from DOING to TODO to generate traffic in the mesh
curl -X POST http://$hostname:$nodePort/mylist/doing/cards/$cardId1/move?to=todo
echo -e ' '
sleep .1


# Move some cards from DOING to DONE to generate traffic in the mesh
curl -X POST http://$hostname:$nodePort/mylist/doing/cards/$cardId2/move?to=done
echo -e ' '
sleep .1