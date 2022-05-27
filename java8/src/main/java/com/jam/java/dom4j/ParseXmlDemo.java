package com.jam.java.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParseXmlDemo {
    public static List<Emp> getXml() {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("java8/src/main/java/com/jam/java/dom4j/emp.xml"));
            List<Emp> list = new ArrayList<>();
            /*
             * 解析第一步，获取根标签（根元素）
             * 这里获取的根标签就相当于是<list>...</list>
             * 那对标签。
             */
            Element root = doc.getRootElement();
            //获取名为"emp"的标签
            List<Element> elementList = root.elements("emp");
            //遍历每一个emp标签
            for (Element empEle : elementList) {
                //获取name
                String name = empEle.elementText("name");
                int age = Integer.parseInt(empEle.elementText("age"));
                String gender = empEle.elementText("gender");
                int salary = Integer.parseInt(empEle.elementText("salary"));
                //属性
                Attribute attr = empEle.attribute("id");
                int id = Integer.parseInt(attr.getValue());
                Emp emp = new Emp(id, name, age, gender, salary);
                list.add(emp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        List<Emp> list = ParseXmlDemo.getXml();
        System.out.println(list);
    }
}