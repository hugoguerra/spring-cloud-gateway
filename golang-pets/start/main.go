package main

import (
	controllers "../controllers"
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
	"net/http"
)

var clientConnect *mongo.Client

func main() {
	connect()

	router := mux.NewRouter()
	path := "/animal"

	router.HandleFunc(path, controllers.RetrieveAll).Methods("GET")
	router.HandleFunc(path, controllers.Save).Methods("POST")
	http.ListenAndServe(":16877", router)
}

func connect() {
	clientOptions := options.Client().ApplyURI("mongodb://localhost:27017")
	clientConnect, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	err = clientConnect.Ping(context.TODO(), nil)

	if err != nil {
		log.Fatal(err)
	}

	filter := bson.D{{}}

	dbs, err := clientConnect.ListDatabaseNames(context.TODO(), filter)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%+v\n", dbs)

	controllers.Collection = clientConnect.Database("lab").Collection("animal")
	fmt.Println("Connected to MongoDB!")
}
