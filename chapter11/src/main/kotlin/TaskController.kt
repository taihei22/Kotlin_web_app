package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Route
import spark.Spark.halt

class TaskController(
    private val objectMapper: ObjectMapper,
    private val taskRepository: TaskRepository
) {

//    fun index(): Route = object : Route{
//        override fun handle(request: Request?, response: Response?): Any =
//                //ダミー
//        listOf(
//            Task(1, "クリーニング", false),
//            Task(2, "住民票", true)
//        )
//    }

    fun index(): Route = Route { req, res ->
        //ダミー
//        listOf(
//            Task(1, "クリーニング", false),
//            Task(2, "住民票", true)
//        )
        taskRepository.findAll()
    }

    fun create(): Route = Route { req, res ->
        val request: TaskCreateRequest =
            objectMapper.readValue(req.bodyAsBytes()) ?: throw halt(400)
        val task = taskRepository.create(request.content)
        res.status(201)
        task
    }

    fun show(): Route = Route { req, res ->
        val id = req.params("id").toLongOrNull()
        id?.let(taskRepository::findById) ?: throw halt(404)
    }

    fun destroy(): Route = Route { req, res ->
        val id = req.params("id").toLongOrNull()
        val task = id?.let(taskRepository::findById) ?: throw halt(404)
        taskRepository.delete(task)
        res.status(204)
    }

    fun update(): Route = Route{ req, res ->
        val request: TaskUpdateRequest =
                objectMapper.readValue(req.bodyAsBytes()) ?: throw halt(400)
        val id = req.params("id").toLongOrNull()
        val task = id?.let(taskRepository::findById) ?: throw halt(404)
        val newTask = task.copy(
            content = request.content ?: task.content,
            done = request.done ?: task.done
        )
        taskRepository.update(newTask)
        res.status(204)


    }
}