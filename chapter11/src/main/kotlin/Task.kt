package todolist

//クラスの前に就職しdataを置いて、データクラスにしている
data class Task(val id: Long,
                val content: String,
                val done: Boolean)