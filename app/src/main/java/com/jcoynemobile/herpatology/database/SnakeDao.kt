package com.jcoynemobile.herpatology.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jcoynemobile.herpatology.Snake
import java.util.*

@Dao
interface SnakeDao {
    @Query("SELECT * FROM snake")
    fun getSnakes(): List<Snake>

    @Query("SELECT * FROM snake WHERE id=(:id)")
    suspend fun getSnake(id: Int): Snake

    @Query ("SELECT * FROM snake WHERE isNortheast=1")
    suspend fun getNortheast(): List<Snake>

    @Query ("SELECT * FROM snake WHERE isNorthwest=1")
    suspend fun getNorthwest(): List<Snake>

    @Query ("SELECT * FROM snake WHERE isSoutheast=1")
    suspend fun getSoutheast(): List<Snake>

    @Query ("SELECT * FROM snake WHERE isSouthwest=1")
    suspend fun getSouthwest(): List<Snake>

    @Query ("SELECT * FROM snake WHERE isCentral=1")
    suspend fun getCentral(): List<Snake>

    @Query ("UPDATE snake SET snakeNotes=:snakeNotes WHERE id=(:id)")
    suspend fun updateNotes(snakeNotes: String, id: Int)

    @Transaction
    suspend fun preloadSnakes() {
        if (getSnakes().isEmpty()) {
            insertSnakes()
        }
    }

    @Query("INSERT INTO snake (name, scientificName, venom, habitat, length, range, color, isNortheast, isNorthwest, isSoutheast, isSouthwest, isCentral, snakeNotes)" +
            "VALUES ('Northern Copperhead', 'Scientific Name: Agkistrodon contortrix mokasen', 'Venomous', 'Habitat: Forest, fields, and rocky outcrops. Can be found under logs.', 'Length: 24-36 inches', 'Range: All over Pennsylvania', 'Coloration: Pink-brown with red bands', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Timber Rattlesnake', 'Scientific Name: Crotalus horridus', 'Venomous', 'Habitat: Rocky outcroppings and forest. Can be found under logs.', 'Length: 36-60 inches', 'Range: Primarily throughout central Pennsylvania', 'Coloration: Yellow-brown with dark chevrons', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Eastern Massasauga', 'Scientific Name: Sistrurus c. catenatus', 'Venomous', 'Habitat: Swamps and wetlands', 'Length: 18-40 inches', 'Range: Western Pennsylvania', 'Coloration: Grey with dark blotches', 0, 1, 0, 1, 0, 'Add notes'), " +
            "('Eastern Worm Snake', 'Scientific Name: Carphophis amoenus', 'Non-venomous', 'Habitat: Rocky and wooded areas. Can be found under logs.', 'Length: 4-9 inches', 'Range: Primarily central Pennsylvania', 'Coloration: Pink-grey top with a light pink bottom', 1, 0, 1, 0, 1, 'Add notes'), " +
            "('Kirtlands Snake', 'Scientific Name: Clonophis kirtlandii', 'Non-venomous', 'Habitat: Wetlands, ponds, and burrows. Can be found under logs.', 'Length: 14-24.5 inches', 'Range: Western Pennsylvania', 'Coloration: Grey top, with a bright red or orange bottom', 0, 1, 0, 1, 0, 'Add notes'), " +
            "('Northern Racer', 'Scientific Name: Coluber constrictor constrictor', 'Non-venomous', 'Habitat: Meadows, farmlands, and rock formations. Can be found under logs', 'Length: 36-60 inches', 'Range: Throughout Pennsylvania', 'Coloration: Black top with a grey bottom', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Ring-Necked Snake', 'Scientific Name: Diadophis punctatus edwardsii', 'Non-venomous', 'Habitat: Rocky areas near streams. Can be found under logs.', 'Length: 12-15 inches', 'Range: Everywhere in Pennsylvania', 'Coloration: Top is grey to olive brown, while bottom is light orange', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Eastern Hognose Snake', 'Scientific Name: Heterodon platirhinos', 'Non-venomous', 'Habitat: Wooded and grassy areas', 'Length: 20-33 inches', 'Range: Found in Central and Eastern Pennsylvania', 'Coloration: Variable. Can be tan, grey, brown, olive, or black', 1, 0, 1, 0, 1, 'Add notes'), " +
            "('Eastern Milksnake', 'Scientific Name: Lampropeltis triangulum triangulum', 'Non-venomous', 'Habitat: Open and rocky areas. Can be found under logs.', 'Length: 24-52 inches', 'Range: Everywhere in Pennsylvania', 'Coloration: Generally pink, brown, grey, or red. Blotches are dark brown and ringed with black', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Northern Water Snake', 'Scientific Name: Nerodia sipedon sipedon', 'Non-venomous', 'Habitat: Aquatic or semi-aquatic environments', 'Length: 24-55 inches', 'Range: Everywhere in Pennsylvania', 'Coloration: Grey-brown to grey, with darker alternating bands', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Northern Rough Greensnake', 'Scientific Name: Opheodrys aestivus', 'Non-venomous', 'Habitat: Moist areas in trees or shrubs, seldomly on the ground', 'Length: 22-32 inches', 'Range: Found in southeastern Pennsylvania', 'Coloration: Uniformly light green', 0, 0, 1, 0, 0, 'Add notes'), " +
            "('Smooth Greensnake', 'Scientific Name: Opheodrys vernalis', 'Non-venomous', 'Habitat: Vegetation, farmland, and meadows', 'Length: 14-20 inches', 'Range: Everywhere except southeastern Pennsylvania', 'Coloration: Uniformly light green', 1, 1, 0, 1, 1, 'Add notes'), " +
            "('Eastern Ratsnake', 'Scientific Name: Pantherophis alleghaniensis', 'Non-venomous', 'Habitat: Meadows, farmland, and open wooded areas. Can be found under logs.', 'Length: 40-101 inches', 'Range: Throughout Pennsylvania', 'Coloration: Black to dull brown tops with light grey bottoms', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Queensnake', 'Scientific Name: Regina septemvittata', 'Non-venomous', 'Habitat: Moving water with good sources of crayfish', 'Length: 14-23 inches', 'Range: Western and southern Pennsylvania', 'Coloration: Olive brown to brown with a yellow stripe on the side', 0, 1, 1, 1, 1, 'Add notes'), " +
            "('Northern Brown Snake', 'Scientific Name: Storeria dekayi dekayi', 'Non-vemonous', 'Habitat: Found in variable habitats. Can be found under logs.', 'Length: 9-13 inches', 'Range: Throughout Pennsylvania', 'Coloration: Grey to brown with a slight red tint. There are y-shaped markings under its eyes', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Northern Redbellied Snake', 'Scientific Name: Storeria occipitomaculata occipitomaculata', 'Non-venomous','Habitat: Forests and moist woodlands. Can be found under logs.', 'Length: 8-16 inches', 'Range: Found everywhere outside of southeastern Pennsylvania', 'Coloration: Highly variable. Top can be grey, brown, to reddish. Bottom is red, tan, or pink', 1, 1, 0, 1, 1, 'Add notes'), " +
            "('Short-headed Garter Snake', 'Scientific Name: Thamnophis brachystoma', 'Non-venomous', 'Habitat: Variable, including underneath logs and debris', 'Length: 18-26 inches', 'Range: Northwestern Pennsylvania', 'Coloration: Olive to olive-brown with 1-2 lighter lateral stripes', 0, 1, 0, 0, 0, 'Add notes'), " +
            "('Ribbonsnake', 'Scientific Name: Thamnophis saurita', 'Non-venomous', 'Habitat: Variable, though preferring areas with persistant water sources', 'Length: 20-34 inches', 'Range:Northern Pennsylvania and southeastern Pennsylvania', 'Coloration: Red, brown, or black with three light yellow lateral stripes', 1, 1, 1, 0, 1, 'Add notes'), " +
            "('Eastern Garter Snake', 'Scientific Name: Thamnophis sirtalis sirtalis', 'Non-venomous', 'Habitat: Found in nearly all habitats', 'Length: 20-28 inches', 'Range: Everywhere in Pennsylvania', 'Coloration: Varies from green, brown, olive, or black, with two lighter lateral stripes', 1, 1, 1, 1, 1, 'Add notes'), " +
            "('Mountain Earthsnake', 'Scientific Name: Virginia pulchra', 'Non-venomous', 'Habitat: Conifer forests in upland regions', 'Length: 7-13 inches', 'Range: Western Central Pennsylvania', 'Coloration: Top is red-brown, brown, or grey, with pale yellow bottom', 0, 1, 0, 1, 0, 'Add notes'), " +
            "('Eastern Smooth Earthsnake', 'Scientific Name: Virginia valeriae valeriae', 'Non-venomous', 'Habitat: All manner of forests. May be found under logs.', 'Length: 7-13 inches', 'Range: Southeastern Pennsylvania', 'Coloration: Grey or brown with a white chin', 0, 0, 1, 0, 1, 'Add notes')")
    suspend fun insertSnakes()
}