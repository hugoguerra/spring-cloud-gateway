package functions

import (
	model "../model"
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
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

func Update(animalId primitive.ObjectID, payload model.Animal, collection *mongo.Collection) *mongo.UpdateResult {

	filter := bson.M{"_id": bson.M{"$eq": animalId}}
	update := bson.M{
		"$set": bson.M{
			"name": payload.Name,
		},
	}
	updateResult, err := collection.UpdateOne(context.TODO(), filter, update)

	if err != nil {
		log.Fatal(err)
	}

	return updateResult
}

func FindById(objectId primitive.ObjectID, collection *mongo.Collection) model.Animal {
	var animal model.Animal
	error := collection.FindOne(context.TODO(), model.Animal{ID: objectId}).Decode(&animal)

	if error != nil {
		return model.Animal{}
	}

	return animal
}
