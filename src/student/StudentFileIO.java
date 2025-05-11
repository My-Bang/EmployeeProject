package student;

import student.inter.StudentIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentFileIO implements StudentIO {

    @Override
    public void input() {}

    @Override
    public void print() {}

    @Override
    public void search() {}

    @Override
    public void sort() {}

    public void delete() {}


    public String mapToJson(Map<String, Object> map) {
        List<String> keyValuePairs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            keyValuePairs.add("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"");
        }
        return "{" + String.join(",", keyValuePairs) + "}";
    }

    public List<Map<String, Object>> jsonToList(String jsonText) {
        List<Map<String, Object>> list = new ArrayList<>();
        jsonText = jsonText.substring(1, jsonText.length() - 1); // 대괄호 제거
        String[] jsonObjects = jsonText.split("\\},\\{"); // 객체 분리
        for (String jsonObject : jsonObjects) {
            jsonObject = jsonObject.replace("{", "").replace("}", ""); // 중괄호 제거
            String[] keyValuePairs = jsonObject.split(",");
            Map<String, Object> map = new HashMap<>();
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].replace("\"", "");
                String value = keyValue[1].replace("\"", "");
                if (key.equals("sno") || key.equals("name")) {
                    map.put(key, value);
                } else {
                    map.put(key, Integer.parseInt(value));
                }
            }
            list.add(map);
        }
        return list;
    }


}
