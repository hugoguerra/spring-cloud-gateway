package functions

import (
	model "../model"
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"log"
	"time"
)

func RetrieveAll(collection *mongo.Collection) []model.Animal {
	var animals []model.Animal
	cursor, err := collection.Find(context.TODO(), bson.M{})
	ctx, _ := context.WithTimeout(context.Background(), 30*time.Second)

	if err != nil {
		return animals
	}

	for cursor.Next(ctx) {
		var animal model.Animal
		cursor.Decode(&animal)
		animals = append(animals, animal)
	}

	return animals
}

func Save(animal model.Animal, collection *mongo.Collection) *mongo.InsertOneResult {
	insertResult, err := collection.InsertOne(context.TODO(), animal)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Inserted a single document: ", insertResult.InsertedID)
	return insertResult
}
