package com.sy.jgrapht.model;

import java.util.Objects;

public class Task {

    private String name;
    private String order;

    public Task(String name) {
        this.name = name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
    }

    public void updateOrder(final String order){
        this.order = this.order+order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return order+ ". " + name;
    }
}
