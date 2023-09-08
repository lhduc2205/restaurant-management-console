package cores.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrettierPrinter {
    public static void print(Object object) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        System.out.println(gson.toJson(object));
    }
}
