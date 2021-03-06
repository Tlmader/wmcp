package wmcp.api.service.impl;

import wmcp.api.repository.INativeQueryRepository;
import wmcp.api.service.INativeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implements service workflow methods for native SQL query usage.
 *
 * @author tlmader.dev@gmail.com
 * @since 11/20/2016
 */
@Service
public class NativeQueryService implements INativeQueryService {

    @Autowired
    INativeQueryRepository repository;

    @Override
    public List<List<Object>> getNativeResults(String query, Map<String, String> vars) {
        return mapNativeResults(repository.get(query, vars));
    }

    @Override
    public List<Map<String, Object>> getNativeResultsWithAttrs(String query, Map<String, String> vars, String attrs) {
        return mapNativeResultsToAttrs(attrs, repository.get(query, vars));
    }

    /**
     * Maps result arrays to Lists.
     *
     * @param results a List of Object[]
     * @return the List of Lists
     */
    private List<List<Object>> mapNativeResults(List<Object[]> results) {
        if (results == null) {
            return null;
        }
        return results.stream().map(Arrays::asList).collect(Collectors.toList());
    }

    /**
     * Maps each ${key} in the given query to its corresponding value and returns the mapped SQL query.
     *
     * @param query a SQL query
     * @return the mapped query
     */
    @Deprecated
    private String mapValuesToKeysForQuery(String query, Map<String, String> vars) {
        for (Map.Entry<String, String> var : vars.entrySet()) {
            query = query.replace("${" + var.getKey() + "}", var.getValue());
        }
        return query;
    }

    /**
     * Maps each result to its corresponding JSON attribute.
     *
     * @param attrs   an array of JSON attributes
     * @param results a List of Object[]
     * @return the List of results with JSON attributes
     */
    private List<Map<String, Object>> mapNativeResultsToAttrs(String attrs, List<Object[]> results) {
        if (results == null) {
            return null;
        }
        String[] attrsArray = attrs.split(",");
        return results.stream().map(r -> IntStream.range(0, r.length).boxed()
                .collect(Collectors.toMap(i -> attrsArray[i], i -> r[i]))).collect(Collectors.toList());
    }
}
