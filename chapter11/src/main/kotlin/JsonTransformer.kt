package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.ResponseTransformer

class JsonTransformer(private val objectMapper: ObjectMapper) : ResponseTransformer{
    override fun render(model: Any?): String =
        //TODO("not implemented") To change body of created functions use File | Settings | File Templates.
        objectMapper.writeValueAsString(model)
    }