package com.verge.theverge.services.seed


import com.verge.theverge.models.ItemsModel
import com.verge.theverge.repository.ItemsRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ItemsSeedService @Autowired constructor (
    private val itemRepository: ItemsRepository
){
    @PostConstruct
    fun seedItems() {
        val items = listOf(
            //Startes
            ItemsModel(id = 1, name = "Artistic Garden", price = BigDecimal("32"), description = "A visually stunning plate featuring an assortment of delicate vegetable preparations and edible flowers, inspired by the artistic presentation of VERVE's dishes"),
            ItemsModel(id = 2, name = "Fusion Tartare", price = BigDecimal("45.80"), description = "A fusion twist on traditional tartare, combining flavors from different cuisines, such as spicy tuna with avocado, mango, and a hint of wasabi."),
            ItemsModel(id = 3, name = "Inovative Seafood Ceviche", price = BigDecimal("52"), description = "A refreshing and innovative ceviche featuring a mix of fresh seafood, citrusy flavors, and unique ingredients like yuzu, inspired by VERVE's fusion approach."),
            //Main Fishes Options
            ItemsModel(id = 4, name = "Elegant Grilled Sea Bass", price = BigDecimal("85.60"), description = "Delicate and beautifully grilled sea bass fillet served with a light citrus butter sauce and a touch of herb-infused olive oil."),
            ItemsModel(id = 5, name = "Fusion Salmon Tataki", price = BigDecimal("94.30"), description = "A fusion-inspired dish featuring seared salmon tataki with a soy ginger glaze, accompanied by a refreshing Asian-style slaw."),
            ItemsModel(id = 6, name = "Innovative Seafood Curry", price = BigDecimal("76.50"), description = "A tantalizing seafood curry with a fusion twist, combining aromatic spices, coconut milk, and a mix of fresh seafood."),
            //Main Course Options
            ItemsModel(id = 7, name = "VERVE's Fusion Wagyu Steak", price = BigDecimal("220.90"), description = "A succulent Wagyu steak infused with bold flavors from different cuisines, accompanied by a creative fusion sauce and vibrant vegetable garnishes."),
            ItemsModel(id = 8, name = "Innovative Seafood Rice", price = BigDecimal("148.10"), description = "A modern twist on a classic Portuguese seafood rice dish, featuring a blend of fresh seafood, exotic spices, and unexpected ingredients."),
            ItemsModel(id = 9, name = "Miso Glazed Black Cod", price = BigDecimal("180.25"), description = "Delicate black cod fillet marinated in a sweet and savory miso glaze, served with stir-fried Asian greens and a side of fluffy jasmine ric."),
            ItemsModel(id = 10, name = "Artistic Sushi Selection", price = BigDecimal("150"), description = "A visually stunning assortment of sushi rolls and nigiri, meticulously prepared and presented with artistic flair."),
            //Dessert Options
            ItemsModel(id = 11, name = "Chocolate Symphony", price = BigDecimal("40"), description = "A decadent chocolate dessert with multiple textures, flavors, and artistic elements."),
            ItemsModel(id = 12, name = "Fusion Tropical Delight", price = BigDecimal("35"), description = "A fusion-inspired dessert combining tropical fruits, delicate pastry, and surprising elements like chili or lemongrass."),
            ItemsModel(id = 13, name = "Deconstructed Cheesecake", price = BigDecimal("42"), description = "A deconstructed version of a classic cheesecake with inventive elements, such as unique crust variations and creative fruit coulis."),
            //Cocktails and Drinks
            ItemsModel(id = 14, name = "Wasabi Papi", price = BigDecimal("14"), description = "Beefater, ginger, lemongrass, and lime juice."),
            ItemsModel(id = 15, name = "European Kiss", price = BigDecimal("16"), description = "Absolut, lime juice, strawberry syrup, jackfruit, passion fruit, and eucalyptus."),
            ItemsModel(id = 16, name = "Japanese Mist", price = BigDecimal("16"), description = "Zacappa 23, pineapple, cilantro, and lemon juice."),
            ItemsModel(id = 17, name = "Mountain Fresh Water", price = BigDecimal("8"), description = "Water obtained from the centuries-old spring in Kanto, Japan."),
            ItemsModel(id = 18, name = "Carlsberg", price = BigDecimal("9"), description = "The best beer in the world."),
            ItemsModel(id = 19, name = "Organe Juice", price = BigDecimal("7"), description = "Juice extracted from the finest oranges from the Alentejo region."),
            ItemsModel(id = 20, name = "Watermelon RedBull", price = BigDecimal("8"), description = "The best flavoured Redbull in the world")
        )
        itemRepository.saveAll(items)
    }
}