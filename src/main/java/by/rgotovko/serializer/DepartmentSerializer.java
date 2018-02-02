package by.rgotovko.serializer;

import by.rgotovko.entity.Department;
import by.rgotovko.entity.Position;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class DepartmentSerializer implements JsonSerializer<Department> {

    @Override
    public JsonElement serialize(Department src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }
}

