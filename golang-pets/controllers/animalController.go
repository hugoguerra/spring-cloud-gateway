package controllers

import (
	functions "../functions"
	model "../model"
	"encoding/json"
	"go.mongodb.org/mongo-driver/mongo"
	"net/http"
)

var Collection *mongo.Collection

func RetrieveAll(w http.ResponseWriter, r *http.Request) {
	json.NewEncoder(w).Encode(functions.RetrieveAll(Collection))
}

func Save(w http.ResponseWriter, r *http.Request) {
	var animalParam model.Animal
	_ = json.NewDecoder(r.Body).Decode(&animalParam)

	json.NewEncoder(w).Encode(functions.Save(animalParam, Collection))
}
