//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
data class Recipe(
    var title: String,
    var description: String = "",
    var ingredients: MutableList<String> = mutableListOf(),
    var steps: MutableList<String> = mutableListOf(),
    var categories: MutableList<String> = mutableListOf()
)
fun main() {
    //val recipes = mutableListOf<Recipe>()

    val recipes = mutableListOf<Recipe>(
        Recipe(
            title = "Spaghetti Bolognese",
            description = "A classic Italian pasta dish with rich meat sauce.",
            ingredients = mutableListOf("Spaghetti", "Ground Beef", "Tomato Sauce", "Onion", "Garlic", "Olive Oil", "Parmesan Cheese"),
            steps = mutableListOf("Boil spaghetti", "Cook ground beef", "Prepare tomato sauce", "Combine and simmer", "Serve with Parmesan cheese"),
            categories = mutableListOf("Italian", "Main Course", "Pasta")
        ),
        Recipe(
            title = "Vegetable Stir-Fry",
            description = "A quick and healthy vegetable stir-fry with soy sauce.",
            ingredients = mutableListOf("Broccoli", "Carrot", "Bell Pepper", "Soy Sauce", "Garlic", "Ginger", "Sesame Oil"),
            steps = mutableListOf("Chop vegetables", "Heat sesame oil", "Add garlic and ginger", "Stir-fry vegetables", "Add soy sauce and cook briefly"),
            categories = mutableListOf("Asian", "Vegetarian", "Quick Meals")
        ),
        Recipe(
            title = "Chocolate Chip Cookies",
            description = "Delicious homemade cookies with chocolate chips.",
            ingredients = mutableListOf("Flour", "Butter", "Sugar", "Brown Sugar", "Eggs", "Vanilla Extract", "Baking Soda", "Chocolate Chips"),
            steps = mutableListOf("Preheat oven", "Mix dry ingredients", "Cream butter and sugars", "Add eggs and vanilla", "Combine wet and dry ingredients", "Fold in chocolate chips", "Bake until golden"),
            categories = mutableListOf("Dessert", "Baking", "Sweet")
        ),
        // Recipes with shared ingredients (Flour, Eggs, Butter)
        Recipe(
            title = "Pancakes",
            description = "Fluffy and golden pancakes perfect for breakfast.",
            ingredients = mutableListOf("Flour", "Eggs", "Butter", "Milk", "Sugar", "Baking Powder", "Vanilla Extract"),
            steps = mutableListOf("Mix dry ingredients", "Whisk wet ingredients", "Combine wet and dry ingredients", "Cook batter on a hot skillet", "Serve with syrup or toppings"),
            categories = mutableListOf("Breakfast", "Dessert", "Quick Meals")
        ),
        Recipe(
            title = "Crepes",
            description = "Thin and delicate French-style pancakes.",
            ingredients = mutableListOf("Flour", "Eggs", "Butter", "Milk", "Salt"),
            steps = mutableListOf("Whisk all ingredients into a smooth batter", "Heat butter in a skillet", "Pour batter and spread thinly", "Cook until edges lift", "Fill or top with sweet or savory options"),
            categories = mutableListOf("French", "Breakfast", "Dessert")
        ),
        Recipe(
            title = "Waffles",
            description = "Crispy on the outside, fluffy on the inside waffles.",
            ingredients = mutableListOf("Flour", "Eggs", "Butter", "Milk", "Sugar", "Baking Powder", "Vanilla Extract"),
            steps = mutableListOf("Preheat waffle iron", "Mix dry ingredients", "Whisk wet ingredients", "Combine wet and dry ingredients", "Cook batter in waffle iron until golden brown"),
            categories = mutableListOf("Breakfast", "Dessert", "Brunch")
        ))



    var programLoop = true
    while (programLoop) {
        println("Welcome to the Recipe Organizer!")
        println("Please select an option from the menu below:")
        println("1. Add a new recipe")
        println("2. View all recipes")
        println("3. Search for a recipe")
        println("4. Edit an existing recipe")
        println("5. Delete a recipe")
        println("6. Exit")
        println("Enter your choice as a number:")

        when (readln()) {

            "1" -> addRecipe(recipes)
            "2" -> viewRecipes(recipes)
            "3" -> searchRecipe(recipes)
            "4" -> editRecipe(recipes)
            "5" -> removeRecipe(recipes)
            "6" -> programLoop = false
            "exit" -> {
                programLoop = false
            }
            else -> {
                println("Invalid input.")
            }

        }

    }
}

fun addRecipe(recipes: MutableList<Recipe>) {
    var loop = true
    while (loop)
    {
        println("type exit to go back")
        println("Write the Title of the Recipe:")
        val title = readln()
        if (title == "exit")
        {
            loop = false;
            break
        }
        println("Write the description of the Recipe:")
        val Description = readln()

        val Ingredients = mutableListOf<String>()
        var Steps = mutableListOf<String>()
        var Catagories = mutableListOf<String>()

        fun addLoop(List: MutableList<String>, printString: String)
        {
            println(printString)
            println("type 'exit' to finish")
            var count = 1
            var loop = true
            while (loop) {
                print("${count} :")
                val item = readln()

                if (item == "exit") {
                    loop = false
                    break
                }
                if (item.isBlank())
                {
                    println("Cannot Be Blank")
                }
                else {
                    List.add(item)
                }
            }
        }

        addLoop(Ingredients,"List Each Ingredient")
        addLoop(Steps,"List Each Step")
        addLoop(Catagories,"List Catagories")

        recipes.add(Recipe(title, Description, Ingredients, Steps, Catagories))
        println("Adding recipe")
    }


}

fun searchRecipe(recipes: MutableList<Recipe>){
    var loop = true
    while (loop) {

        println("Search All Recipes")
        println("Type 'exit' to finish")
        println("Type what you are searching: ")

        val query = readln()
        if (query.lowercase().contains("exit")) {
            loop = false
            break
        }
        val results = recipes.flatMap { recipe ->
            listOfNotNull(
                recipe.takeIf { it.title.contains(query, ignoreCase = true) },
                recipe.takeIf { it.description.contains(query, ignoreCase = true) },
                recipe.takeIf { it.ingredients.any { ingredient -> ingredient.contains(query, ignoreCase = true) } },
                recipe.takeIf { it.steps.any { step -> step.contains(query, ignoreCase = true) } },
                recipe.takeIf { it.categories.any { category -> category.contains(query, ignoreCase = true) } }
            )
        }


        if (results.isNotEmpty()) {
            for (recipe in results.distinct()) {
                println("\nTitle: ${recipe.title}")
                println("Description: ${recipe.description}")
                println("Ingredients: ${recipe.ingredients.joinToString(", ")}")
                println("Steps: ${recipe.steps.joinToString(" -> ")}")
                println("Categories: ${recipe.categories.joinToString(", ")}")
            }
        } else println("No results found for '${query}'.")
    }
}

fun editRecipe(recipes: MutableList<Recipe>) {
    val choice = chooseRecipe(recipes)
    val choiceRecipe = recipes[choice]
    println("Choose what to edit")
    println("1: Title")
    println("2: Description")
    println("3: Ingredients")
    println("4: Steps")
    println("5: Catagories")

    var type = ""
    val selection = readln()
    var list = mutableListOf<String>()
    when (selection) {
        "1" -> { println(choiceRecipe.title)
           println("Write the new Title of the Recipe:") }
        "2" -> {println(choiceRecipe.description)
            println("Write the Description of the Recipe:") }
        "3" -> {println(choiceRecipe.ingredients)
            list = choiceRecipe.ingredients
            type = "Ingredients"}
        "4" -> {println(choiceRecipe.steps)
            list = choiceRecipe.steps
            type = "Steps"}
        "5" -> {
            println(choiceRecipe.categories)
            list = choiceRecipe.categories
            type = "Categorys"}
    }

    fun removeItems(list: MutableList<String>, type: String)
    {
        var ChooseLoop = true
        while (ChooseLoop) {
            var count = 1
            for (item in list)
            {
                println("${count}:${item}")
                count ++
            }

            println("choose an ${type} to remove")
            val listChoice = readln()
            if (listChoice.isNotBlank()){
                if (listChoice?.lowercase() == "exit") {
                    ChooseLoop = false
                    break
                }
                val indexResponse = listChoice?.toIntOrNull()
                if (indexResponse != null && indexResponse-1 in recipes.indices) {
                    println("removed : ${list[indexResponse - 1]}")
                    list.removeAt(indexResponse - 1)
                }
            }
        }
    }
    fun addItems(list: MutableList<String>, type: String)
    {
        var ChooseLoop = true
        while (ChooseLoop) {
            var count = 1
            for (item in list)
            {
                println("${count}:${item}")
                count ++
            }

            println("Type the name of the ${type}")
            val newItem = readln()
            if (newItem.lowercase() == "exit"){
                ChooseLoop = false
                break
            };
            list.add(newItem)
        }
    }
    if (list.isNotEmpty())
    {
        var count = 1
        for (item in list)
        {
            println("${count}:${item}")
            count ++
        }
        println("1:Add Items \n2:Remove Items")
        val choice2 = readln()
        when (choice2.lowercase()) {
            "1" -> {addItems(list, type)}
            "2" -> {removeItems(list,type)}
            "add" -> {addItems(list,type)}
            "remove" -> {removeItems(list,type)}
        }

    }
    else{
        when(selection) {
            "1" ->  {
                println("Current Recipe Title: ${choiceRecipe.title}")
                println("Write the new Title of the Recipe:")
                val newTitle = readln()
                if (newTitle == "exit")
                {
                    println("No Change Made to ${choiceRecipe.title}")
                }
                else if (newTitle.isNotBlank())
                {
                    choiceRecipe.title = newTitle;
                }
                else
                {
                    println("Error, no text detected")
                }
            }
            "2" -> {
                println("Current Recipe Description: ${choiceRecipe.description}")
                println("Write the new description of the Recipe:")
                val newDescription = readln()
                if (newDescription == "exit")
                {
                    println("No Change Made to ${choiceRecipe.description}")
                }
                else if (newDescription.isNotBlank())
                {
                    choiceRecipe.description = newDescription;
                }
                else {
                    println("Error, no text detected")
                }
            }
        }
    }

}

fun removeRecipe(recipes: MutableList<Recipe>) {
    if (recipes.isNotEmpty()) {
        val choice = chooseRecipe(recipes)
        if (choice != -1)
        {
            println("removed : ${recipes[choice].title}")
            recipes.removeAt(choice)
        }
    }
}

fun viewRecipes(recipes: MutableList<Recipe>) {
    if (recipes.isNotEmpty())
    {
        var count = 1
        for (recipe in recipes) {
            println("${count}: ${recipe.title}")
            count++

        }
    }
    else
    {
        print("No Recipes Found")
    }
}


fun chooseRecipe(recipes: MutableList<Recipe>): Int {
    var ChooseLoop = true

    while (ChooseLoop) {
        if (recipes.isEmpty()) {
            ChooseLoop = false
            println("no recipes to remove")
            return -1
        }
        println("type exit to go back")
        println("Choose a Recipe")
        ~(recipes)

        val response = readLine()
        if (response?.lowercase() == "exit") {
            ChooseLoop = false
            return -1
        }
        val indexResponse = response?.toIntOrNull()
        if (indexResponse != null && indexResponse-1 in recipes.indices) {
            return (indexResponse - 1)
        }

    }
    return -1
}
