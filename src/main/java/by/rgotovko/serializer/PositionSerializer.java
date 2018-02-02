package by.rgotovko.serializer;

import by.rgotovko.entity.Position;
import by.rgotovko.entity.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PositionSerializer implements JsonSerializer<Position> {
    @Override
    public JsonElement serialize(Position src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTitle());
    }
}

