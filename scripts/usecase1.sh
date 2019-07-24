#!/usr/bin/env bash

# Un premier scenario simple, création d'un client et d'un compte pour ce client
urlApiClients="http://localhost:8080/clients"
urlApiAccounts="http://localhost:8080/accounts"

requestBody=$(jq -n '{ "name": "ragnard", "email": "ragnard.lodbrock@gmail.com"}')
clientLocation=$(curl -v POST ${urlApiClients} -d "${requestBody}" -H 'Content-Type: application/json' 2>&1 | grep Location)

searchString="Location: http://localhost:8080/clients/"
clientId=${clientLocation#*$searchString}

echo "Nouveau client avec l'id : ${clientId}"

# On construit le corps de la requête avec jq
requestBody=$(jq -n --arg clientId "$clientId" '{clientId: $clientId}')

accountLocation=$(curl -v POST ${urlApiAccounts} -d "${requestBody}" -H 'Content-Type: application/json' 2>&1 | grep Location)

searchString="Location: http://localhost:8080/accounts/"
accountId=${accountLocation#*$searchString}

echo "Nouveau compte avec l'id : ${accountId}"