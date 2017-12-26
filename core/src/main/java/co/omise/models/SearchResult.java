package co.omise.models;

import java.util.Map;

public class SearchResult<T extends OmiseObject> extends OmiseList<T> {
    private SearchScope scope;
    private String query;
    private Map<String, String> filters;
    private int page;
    private int totalPages;

    public SearchScope getScope() {
        return scope;
    }

    public void setScope(SearchScope scope) {
        this.scope = scope;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
