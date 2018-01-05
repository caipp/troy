package com.troy.persistence;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-05
 */
public class SearchFilter {
    public enum Operator {
        EQ, LIKE, GT, LT, GTE, LTE
    }

    public String fieldName;
    public Object value;
    public Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public static Map<String, SearchFilter> parse(Map<String, Object> filterParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();

        for (Entry<String, Object> entry : filterParams.entrySet()) {
            String[] names = StringUtils.split(entry.getKey(), "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(entry.getKey() + " is not a valid search filter name");
            }
            if(entry.getValue() != null ){
                if(entry.getValue() instanceof String ){
                    if(StringUtils.isNotEmpty(entry.getValue().toString())){
                        SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), entry.getValue());
                        filters.put(filter.fieldName, filter);
                    }

                }else{
                    SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), entry.getValue());
                    filters.put(filter.fieldName, filter);
                }


            }

        }

        return filters;
    }
}
