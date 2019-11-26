package controllers

import (
	functions "../functions"
	model "../model"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"net/http"
)

var Collection *mongo.Collection

func RetrieveAll(w http.ResponseWriter, r *http.Request) {
	fmt.Println("RetrieveAll")
	json.NewEncoder(w).Encode(functions.RetrieveAll(Collection))
}

func Save(w http.ResponseWriter, r *http.Request) {
	var animalParam model.Animal
	json.NewDecoder(r.Body).Decode(&animalParam)

	json.NewEncoder(w).Encode(functions.Save(animalParam, Collection))
}

func Update(w http.ResponseWriter, r *http.Request) {
	var payload model.Animal

	animal := findAnimalById(w, r)
	json.NewDecoder(r.Body).Decode(&payload)

	json.NewEncoder(w).Encode(functions.Update(animal.ID, payload, Collection))
}

func FindById(w http.ResponseWriter, r *http.Request) {
	animalFound := findAnimalById(w, r)

	if animalFound.ID == primitive.NilObjectID {
		return
	}

	json.NewEncoder(w).Encode(animalFound)
}

func findAnimalById(w http.ResponseWriter, r *http.Request) model.Animal {
	params := mux.Vars(r)
	idAnimal, error := primitive.ObjectIDFromHex(params["id"])

	if error != nil {
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(`{ "message": ID can not be null" }`))
		return model.Animal{}
	}

	animal := functions.FindById(idAnimal, Collection)

	if animal.ID == primitive.NilObjectID {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte(`{ "message": Animal not found" }`))
		return model.Animal{}
	}

	return animal
}
