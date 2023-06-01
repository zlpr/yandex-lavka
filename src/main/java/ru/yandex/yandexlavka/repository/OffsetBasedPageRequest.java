package ru.yandex.yandexlavka.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetBasedPageRequest implements Pageable {
    private final int limit;
    private final int offset;

    private final Sort sort;
    public OffsetBasedPageRequest(int offset, int limit, Sort sort) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }
    @Override
    public int getPageSize() {
        return limit;
    }
    @Override
    public long getOffset() {
        return offset;
    }
    @Override
    public Sort getSort() {
        return sort;
    }
    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest((int) (getOffset() + getPageSize()), getPageSize(), sort);
    }
    public Pageable previous() {
        return hasPrevious()
                ? new OffsetBasedPageRequest((int) (getOffset() - getPageSize()), getPageSize(), sort)
                : this;
    }
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }
    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetBasedPageRequest(limit * pageNumber, limit, sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}