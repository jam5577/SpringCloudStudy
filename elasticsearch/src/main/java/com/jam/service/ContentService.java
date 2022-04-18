package com.jam.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ContentService {

    //解析数据放入es中
    boolean parseContent(String keyword) throws IOException;

    //从es中获取数据进行搜索
    List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException;

}
