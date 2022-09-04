package com.ensas.librarymanagementsystem.utils;

import org.springframework.stereotype.Component;

@Component
public class GeneratePagination {

    public int[] pagination(int requestedPage) {
        int[] pagination = null;
        if (requestedPage == 0 || requestedPage == 1 || requestedPage == 2){
            pagination = new int[]{0, 1, 2};
        } else if (requestedPage % 3 == 0) {
            pagination = new int[]{requestedPage, requestedPage + 1, requestedPage + 2};
        } else if (requestedPage % 3 == 1) {
            pagination = new int[]{requestedPage - 1, requestedPage, requestedPage + 1};
        } else if (requestedPage % 3 == 2) {
            pagination = new int[]{requestedPage - 2, requestedPage - 1, requestedPage};
        }
        return pagination;
    }
}
