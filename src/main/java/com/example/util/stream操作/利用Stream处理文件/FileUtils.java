package com.example.util.stream操作.利用Stream处理文件;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * 文件处理工具
 *
 * @author yuwb
 * @date 18-9-2 上午9:33
 */
public class FileUtils {

    /**
     * 利用流对文件内容进行处理
     *
     * @return 按格式输出的结果
     */
    public static HashMap<String, District> readFileByStream() {
        HashMap<String, District> result = new HashMap<>(16);

        try (Stream<String> lines = Files.lines(Paths.get("city"), Charset.defaultCharset())) {
            //得到指定对象的集合
            List<District> districtList = lines.map(line -> line.split(" ")).skip(1).filter(array -> array.length > 1).map(array -> new District(array[0], array[1], array[2])).collect(Collectors.toList());
            //以区号为分组，得到指定对象的集合
            Map<String, List<District>> districtListMap = districtList.stream().filter(district -> district.getName().contains("区")).collect(groupingBy(District::getAreaCode));
            //生成以地区名字为键，地区对象为值的map
            Map<String, District> districtMap = districtList.stream().collect(toMap(District::getName, district -> district));

            //对集合进行遍历，将地区注入到对应的市区中
            for (District district : districtMap.values()) {
                if (district.getName().contains("市")) {
                    for (Map.Entry<String, List<District>> areas : districtListMap.entrySet()) {
                        if (Objects.equals(district.getAreaCode(), areas.getKey())) {
                            City city = new City(district.getName(), district.getAreaCode(), district.getZipCode(), areas.getValue());
                            districtMap.put(district.getName(), city);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过字符流对文件内容进行处理
     * 待优化
     *
     * @return 按格式输出的结果
     * @throws IOException io异常
     */
    public static HashMap<String, Object> readFile() throws IOException {
        HashMap<String, Object> result = new HashMap<>(16);
        HashMap<String, List<District>> areas = new HashMap<>(16);
        String area;

        try (BufferedReader bf = new BufferedReader(new FileReader(new File("city")))) {
            while (true) {
                if ((area = bf.readLine()) != null) {
                    String[] district = area.split(" ");
                    if (district[0].contains("市")) {
                        City city = new City(district[0], district[1], district[2]);
                        result.put(district[0], city);
                    }

                    if (district[0].contains("区")) {
                        District district1 = new District(district[0], district[1], district[2]);
                        result.put(district[0], district1);
                        List<District> list = areas.get(district[1]);
                        if (list != null) {
                            list.add(district1);
                            areas.put(district[1], list);
                        } else {
                            List<District> list1 = new ArrayList<>();
                            list1.add(district1);
                            areas.put(district[1], list1);
                        }
                    }
                } else {
                    break;
                }
            }

            for (Map.Entry<String, List<District>> listEntry : areas.entrySet()) {
                for (Map.Entry<String, Object> stringObjectEntry : result.entrySet()) {
                    if (stringObjectEntry.getKey().contains("市")) {
                        City city = (City) stringObjectEntry.getValue();
                        if (city.getAreaCode().equals(listEntry.getKey())) {
                            city.setDistricts(listEntry.getValue());
                            result.put(city.getName(), city);
                        }
                    }
                }
            }
        }
        return result;
    }
}
