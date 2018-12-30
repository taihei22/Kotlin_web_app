package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.get
import spark.Spark.post


//fun main(args: Array<String>){
//    val objectMapper = ObjectMapper().registerKotlinModule()
//    get("/tasks",{ req, res ->
//        listOf(
//            Task(1, "クリーニング", false),
//            Task(2, "住民票", true)
//        )
//    }, objectMapper::writeValueAsString)

fun main(args: Array<String>){
    val objectMapper = ObjectMapper().registerKotlinModule()
    val jsonTransformer = JsonTransformer(objectMapper)
    val taskRepository = TaskRepository()
    val taskController = TaskController(objectMapper, taskRepository)

    get("/tasks", taskController.index(), jsonTransformer)
    post("/tasks", taskController.create(), jsonTransformer)
}