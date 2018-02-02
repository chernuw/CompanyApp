package by.rgotovko.serializer;

import by.rgotovko.entity.Employee;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;

public class EmployeeSerializer implements JsonSerializer<Employee>{
    @Override
    public JsonElement serialize(Employee src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("employeeId", src.getId());
        object.addProperty("phone", src.getPhone());
        object.addProperty("email", src.getEmail());
        object.addProperty("firstName", src.getFirstName());
        object.addProperty("lastName", src.getLastName());
        object.addProperty("middleName", src.getMiddleName());
        object.addProperty("address", src.getAddress());
        object.addProperty("birthDate", String.valueOf(src.getBirthDate()));
        object.addProperty("hireDate", String.valueOf((src.getHireDate())));
        object.add("department", context.serialize(src.getDepartment()));
        object.add("position", context.serialize(src.getPosition()));
        object.add("user", context.serialize(src.getUser()));
        return object;
    }
}

