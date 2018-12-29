import spark.Spark.get

fun main(args: Array<String>){
    get("/hello"){ request, response ->
        //"Hello, world"
        val name: String? = request.queryParams("name")
        //以下の部分は「エルビス演算子」を使っている
        "Hello, ${name ?: "world"}!"
    }
}