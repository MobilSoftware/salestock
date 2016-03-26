package com.mascova.talarion.web.rest.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a
 * href="https://developer.github.com/v3/#pagination">Github API</a>, and follow <a
 * href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public class PaginationUtil {

  public static final int DEFAULT_OFFSET = 1;

  public static final int MIN_OFFSET = 1;

  public static final int DEFAULT_LIMIT = 10;

  public static final int MAX_LIMIT = 100;

  public static Pageable generatePageRequest(Integer offset, Integer limit) {
    if (offset == null || offset < MIN_OFFSET) {
      offset = DEFAULT_OFFSET;
    }
    if (limit == null || limit > MAX_LIMIT) {
      limit = DEFAULT_LIMIT;
    }
    return new PageRequest(offset - 1, limit);
  }

  public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl)
      throws URISyntaxException {

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Total-Count", "" + page.getTotalElements());
    String link = "";
    if ((page.getNumber() + 1) < page.getTotalPages()) {
      link = "<"
          + (new URI(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize()))
              .toString() + ">; rel=\"next\",";
    }
    // prev link
    if ((page.getNumber()) > 0) {
      link += "<"
          + (new URI(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize()))
              .toString() + ">; rel=\"prev\",";
    }
    // last and first link
    int lastPage = 0;
    if (page.getTotalPages() > 0) {
      lastPage = page.getTotalPages() - 1;
    }
    link += "<" + (new URI(baseUrl + "?page=" + lastPage + "&size=" + page.getSize())).toString()
        + ">; rel=\"last\",";
    link += "<" + (new URI(baseUrl + "?page=" + 0 + "&size=" + page.getSize())).toString()
        + ">; rel=\"first\"";
    headers.add(HttpHeaders.LINK, link);
    return headers;
  }
}
